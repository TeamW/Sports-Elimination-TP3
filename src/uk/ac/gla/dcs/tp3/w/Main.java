/**
 * 
 */
package uk.ac.gla.dcs.tp3.w;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Match;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.Parser;;

/**
 * @author gordon
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	
	
	
	public static void main(String[] args) {
		// Create test division.
		//Division testDivision = testUIAndAlgorithm();
		
		// Create algorithm for division.
		//Algorithm algorithm = new Algorithm(testDivision);
		
		// Find out what teams are eliminated.
		//for (Team t : testDivision.getTeams()) {
			//t.setEliminated(algorithm.isEliminated(t));
		//}
		
		// import the file 
		Parser p =null;
		
		try {
		File source = new File(System.getProperty("user.dir")
				+ "/src/uk/ac/gla/dcs/tp3/w/parser/baseballSource.txt");
		
		 p = new Parser(source);		
		
		
		}catch (Exception e) {
			System.out.println("Wrong filename");
		}
				
		//Test to see if Parser is working
		
		System.out.println(p.getAmericanCentral());
		System.out.println(p.getAmericanEast());
		System.out.println(p.getAmericanWest());
		
		System.out.println(p.getNationalCentral());
		System.out.println(p.getNationalEast());
		System.out.println(p.getNationalWest());
		
		// Create algorithm for division.
		Algorithm algorithm = new Algorithm(p.getAmericanCentral());
		
		
		// Find out what teams are eliminated.
		for (Team t : p.getAmericanCentral().getTeams()) {
			t.setEliminated(algorithm.isEliminated(t));
		}
		
		
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

		return new Division(teams, allMatches);
	}

}
