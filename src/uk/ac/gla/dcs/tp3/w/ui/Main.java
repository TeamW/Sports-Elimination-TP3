package uk.ac.gla.dcs.tp3.w.ui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Match;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(getTestDivision());
			}
		});
	}

	public static HashMap<String, Division> getTestDivision() {
		Team atlanta = new Team();
		atlanta.setName("Atlanta");
		atlanta.setGamesPlayed(170 - 8);
		atlanta.setPoints(83);
		atlanta.setNational(true);

		Team philadelphia = new Team();
		philadelphia.setName("Philadelphia");
		philadelphia.setGamesPlayed(170 - 4);
		philadelphia.setPoints(79);

		Team newYork = new Team();
		newYork.setName("New York");
		newYork.setGamesPlayed(170 - 7);
		newYork.setPoints(78);
		newYork.setNational(false);

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

		Division d = new Division(teams, allMatches);
		Algorithm a = new Algorithm(d);
		for (Team t : teams)
			t.setEliminated(a.isEliminated(t));
		HashMap<String, Division> map = new HashMap<String, Division>();
		map.put("American Central", d);
		return map;
	}

}
