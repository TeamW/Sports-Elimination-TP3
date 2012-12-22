package uk.ac.gla.dcs.tp3.w;

import java.io.File;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.Parser;
import uk.ac.gla.dcs.tp3.w.ui.MainFrame;

public class Main {

	public static void main(String[] args) {
		Parser p = null;
		File source = new File(System.getProperty("user.dir")
				+ "/src/uk/ac/gla/dcs/tp3/w/parser/baseballSource.txt");
		if (source.exists())
			p = new Parser(source);
		else
			System.err.println("File not found.");
		if (p == null)
			return;

		// Create algorithm for division.
		Algorithm algorithm = new Algorithm(p.getAmericanCentral());

		final HashMap<String, Division> map = new HashMap<String, Division>();
		map.put("American Central", p.getAmericanCentral());
		map.put("American East", p.getAmericanEast());
		map.put("American West", p.getAmericanWest());
		map.put("National Central", p.getNationalCentral());
		map.put("National East", p.getNationalEast());
		map.put("National West", p.getNationalWest());
		for (Division d : map.values()) {
			algorithm = new Algorithm(d);
			for (Team t : d.getTeams()) {
				algorithm.isEliminated(t);
				System.out.println(t.getUpcomingMatches().size());
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(map);
			}
		});
	}
}
