package uk.ac.gla.dcs.tp3.w;

import java.util.HashMap;

import javax.swing.SwingUtilities;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.Parser;
import uk.ac.gla.dcs.tp3.w.ui.MainFrame;

public class Main {

	public static void main(String[] args) {
		Parser p = new Parser();
		boolean web = false;
		boolean parsed = false;
		for (String s : args)
			if (s.equals("--web"))
				web = true;
			else
				parsed = p.parse(s);
		if (!parsed)
			p.parse("");

		final HashMap<String, Division> map = p.getDivisions();
		Algorithm algorithm = new Algorithm();
		for (Division d : map.values()) {
			algorithm = new Algorithm(d);
			for (Team t : d.getTeams())
				t.setEliminated(algorithm.isEliminated(t));
		}
		if (!web) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new MainFrame(map);
				}
			});
		} else {
			for (Division d : map.values())
				d.printWeb();
		}
	}
}
