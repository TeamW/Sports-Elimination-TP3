package uk.ac.gla.dcs.tp3.w.parser;

import java.io.File;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// import the file
		Parser p = null;

		File source = new File(System.getProperty("user.dir")
				+ "/src/uk/ac/gla/dcs/tp3/w/parser/baseballSource.txt");
		if (source.exists()) {
			p = new Parser(source);
		} else {
			System.err.println("File not found.");
			return;
		}

		if (p.isVerbose()) {
			System.out.println(p.getAmericanCentral());
			System.out.println(p.getAmericanEast());
			System.out.println(p.getAmericanWest());
			System.out.println(p.getNationalCentral());
			System.out.println(p.getNationalEast());
			System.out.println(p.getNationalWest());
		}

		// Create algorithm for division.
		Algorithm algorithm = new Algorithm(p.getAmericanCentral());

		// Find out what teams are eliminated.
		for (Team t : p.getAmericanCentral().getTeams()) {
			t.setEliminated(algorithm.isEliminated(t));
		}

	}

}
