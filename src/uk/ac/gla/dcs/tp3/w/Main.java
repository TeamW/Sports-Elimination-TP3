package uk.ac.gla.dcs.tp3.w;

import java.io.File;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.AltParser;
import uk.ac.gla.dcs.tp3.w.ui.MainFrame;

public class Main {

	private static final String DEFAULT_FILE = System.getProperty("user.dir")
			+ "/src/uk/ac/gla/dcs/tp3/w/parser/baseballSource.txt";

	public static void main(String[] args) {
		AltParser p = new AltParser();
		File source;
		if (args.length == 0) {
			System.out.println(DEFAULT_FILE);
			source = new File(DEFAULT_FILE);
		} else
			source = new File(args[0]);
		if (source.exists())
			p.parse(source.getAbsolutePath());
		else
			System.err.println("File not found.");

		final HashMap<String, Division> map = p.getDivisions();
		Algorithm algorithm = new Algorithm();
		for (Division d : map.values()) {
			algorithm = new Algorithm(d);
			for (Team t : d.getTeams())
				t.setEliminated(algorithm.isEliminated(t));
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(map);
			}
		});
	}
}
