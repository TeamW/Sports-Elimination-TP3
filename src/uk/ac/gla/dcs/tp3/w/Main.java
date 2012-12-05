/**
 * 
 */
package uk.ac.gla.dcs.tp3.w;

import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Match;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.Parser;
import uk.ac.gla.dcs.tp3.w.ui.UI;

/**
 * @author gordon
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parser p = null;

		File source = new File(System.getProperty("user.dir")
				+ "/src/uk/ac/gla/dcs/tp3/w/parser/baseballSource.txt");
		if (source.exists()) {
			p = new Parser(source);
		} else {
			System.err.println("File not found.");
			return;
		}
		
		// Create algorithm for division.
		Algorithm algorithm = new Algorithm(p.getAmericanCentral());

		// Find out what teams are eliminated.
		for (Team t : p.getAmericanCentral().getTeams()) {
			t.setEliminated(algorithm.isEliminated(t));
		}
		
		final Division d = p.getAmericanCentral();

		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UI ui = new UI(d);
				ui.setVisible(true);
			}
		});
	}

	private static Division testUIAndAlgorithm() {
		Team atlanta = new Team();
		atlanta.setName("Atlanta");
		atlanta.setGamesPlayed(170 - 8);
		atlanta.setPoints(83);

		Team philadelphia = new Team();
		philadelphia.setName("Philadelphia");
		philadelphia.setGamesPlayed(170 - 4);
		philadelphia.setPoints(79);

		Team newYork = new Team();
		newYork.setName("New York");
		newYork.setGamesPlayed(170 - 7);
		newYork.setPoints(78);

		Team montreal = new Team();
		montreal.setName("Montreal");
		montreal.setGamesPlayed(170 - 5);
		montreal.setPoints(76);

		ArrayList<Match> atlantaMatches = new ArrayList<Match>();
		ArrayList<Match> philadelphiaMatches = new ArrayList<Match>();
		ArrayList<Match> newYorkMatches = new ArrayList<Match>();
		ArrayList<Match> montrealMatches = new ArrayList<Match>();
		ArrayList<Match> allMatches = new ArrayList<Match>();

		Match atlVphil = new Match();
		atlVphil.setHomeTeam(atlanta);
		atlVphil.setAwayTeam(philadelphia);
		atlantaMatches.add(atlVphil);
		philadelphiaMatches.add(atlVphil);
		allMatches.add(atlVphil);

		Match atlVny = new Match();
		atlVny.setHomeTeam(atlanta);
		atlVny.setAwayTeam(newYork);
		for (int i = 0; i < 6; i++) {
			atlantaMatches.add(atlVny);
			newYorkMatches.add(atlVny);
			allMatches.add(atlVny);
		}

		Match atlVmon = new Match();
		atlVmon.setHomeTeam(atlanta);
		atlVmon.setAwayTeam(montreal);
		atlantaMatches.add(atlVmon);
		montrealMatches.add(atlVmon);
		allMatches.add(atlVmon);

		Match philVmon = new Match();
		philVmon.setHomeTeam(philadelphia);
		philVmon.setAwayTeam(montreal);
		for (int i = 0; i < 3; i++) {
			philadelphiaMatches.add(philVmon);
			montrealMatches.add(philVmon);
			allMatches.add(philVmon);

		}

		Match nyVmon = new Match();
		nyVmon.setHomeTeam(newYork);
		nyVmon.setAwayTeam(montreal);
		newYorkMatches.add(nyVmon);
		montrealMatches.add(nyVmon);
		allMatches.add(nyVmon);

		atlanta.setUpcomingMatches(atlantaMatches);
		philadelphia.setUpcomingMatches(philadelphiaMatches);
		newYork.setUpcomingMatches(newYorkMatches);
		montreal.setUpcomingMatches(montrealMatches);

		ArrayList<Team> teams = new ArrayList<Team>();
		teams.add(atlanta);
		teams.add(philadelphia);
		teams.add(newYork);
		teams.add(montreal);

		Division testDivision = new Division(teams, allMatches);
		// Create algorithm for division.
		Algorithm algorithm = new Algorithm(testDivision);
		// Find out what teams are eliminated.
		for (Team t : testDivision.getTeams()) {
			t.setEliminated(algorithm.isEliminated(t));
		}
		return new Division(teams, allMatches);
	}

}
