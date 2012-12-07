package uk.ac.gla.dcs.tp3.w;

import java.io.File;

import javax.swing.SwingUtilities;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.Parser;
import uk.ac.gla.dcs.tp3.w.ui.UI;

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
		// Find out what teams are eliminated.
		for (Team t : p.getAmericanCentral().getTeams())
			t.setEliminated(algorithm.isEliminated(t));
		final Division d = p.getAmericanCentral();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UI ui = new UI(d);
				ui.setVisible(true);
			}
		});
	}
}
