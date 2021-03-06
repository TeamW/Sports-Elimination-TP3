package uk.ac.gla.dcs.tp3.w.league;

import java.util.ArrayList;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;

/**
 * This class has two members: an array of teams; and an array of fixtures. The
 * primary purpose of this class is to maintain, and keep track of, progress
 * through a given sports league.
 * 
 * @author Team W
 * @version 1.0
 */
public class Division {

	private String name;
	private ArrayList<Team> teams;
	private ArrayList<Match> fixtures;
	private Team firstNTTeamElim;
	private Date firstNTTeamElimdate;

	public Team getFirstNTTeamElim() {
		return firstNTTeamElim;
	}

	public void setFirstNTTeamElim(Team firstNTTeamElim) {
		this.firstNTTeamElim = firstNTTeamElim;
	}

	public Date getFirstNTTeamElimdate() {
		return firstNTTeamElimdate;
	}

	public void setFirstNTTeamElimdate(Date firstNTTeamElimdate) {
		this.firstNTTeamElimdate = firstNTTeamElimdate;
	}

	/**
	 * No parameter constructor.
	 */
	public Division() {
		this("");
	}

	/**
	 * A typical constructor, each instance variable has an associated
	 * parameter.
	 * 
	 * @param t
	 *            The array of teams in this league.
	 * @param m
	 *            The array of matches in this league.
	 */
	public Division(ArrayList<Team> t, ArrayList<Match> m) {
		name = "";
		teams = t;
		fixtures = m;

	}

	/**
	 * Create a blank division with an associated name.
	 * 
	 * @param s
	 *            Name of division string
	 */
	public Division(String s) {
		name = s;
		teams = new ArrayList<Team>();
		fixtures = new ArrayList<Match>();
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		name = s;
	}

	/**
	 * Get the list of teams in the sports league
	 * 
	 * @return Team array of participating teams
	 */
	public ArrayList<Team> getTeams() {
		return teams;
	}

	/**
	 * Set the list of teams in the sports league
	 * 
	 * @param teams
	 *            Team array of participating teams
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	/**
	 * Get the list of matches in the sports league
	 * 
	 * @return Match array of all matches
	 */
	public ArrayList<Match> getFixtures() {
		return fixtures;
	}

	/**
	 * Set the list of matches in the sports league
	 * 
	 * @param fixtures
	 *            Match array of all matches
	 */
	public void setFixtures(ArrayList<Match> fixtures) {
		this.fixtures = fixtures;
	}

	/**
	 * Adds given match into the fixture array if not currently in the array.
	 * Also, if the match has not been played, calls the appropriate Team method
	 * to add the match to the upcoming match array for the associated teams.
	 * 
	 * @param m
	 *            The match to add to the fixture list.
	 */
	public void addFixture(Match m) {
		if (!fixtures.contains(m)) {
			fixtures.add(m);
		}
	}

	/**
	 * Adds given team into the team array if not currently in the array.
	 * 
	 * @param t
	 *            The team to be added to the team array.
	 */
	public void addTeam(Team t) {
		if (!teams.contains(t)) {
			teams.add(t);
		}
	}

	/**
	 * Returns true if the given team object is contained within the team array.
	 * 
	 * @param t
	 *            The team being tested.
	 * @return True if team is in the list, false otherwise.
	 */
	public boolean isMember(Team t) {
		return (!teams.isEmpty()) ? teams.contains(t) : false;
	}

	/**
	 * Return a string of division fixtures and division teams with appropriate
	 * headers.
	 */
	public String toString() {
		return String.format("%s\n%s\n%s\n%s\n", "Division fixtures", fixtures,
				"Division teams", teams);
	}

	/**
	 * Return the maximum number of points a team in the division currently has.
	 * 
	 * @return integer representing points of the top team in the division.
	 */
	public int maxPoints() {
		Team[] t = teamsToArray();
		int max = 0;
		for (Team team : t) {
			if (team.getPoints() > max) {
				max = team.getPoints();
			}
		}
		return max;
	}

	/**
	 * Returns an array of teams. This is used instead of toArray() since that
	 * method returns an array of objects that cannot be easily cast by the
	 * client to a Team array.
	 * 
	 * @return A Team array (Team[]) of every team participating in the
	 *         division.
	 */
	public Team[] teamsToArray() {
		Team[] array = new Team[teams.size()];
		int i = 0;
		for (Team t : teams) {
			array[i++] = t;
		}
		return array;
	}

	/**
	 * Print out the information of the division's league table in a format
	 * suitable for parsing for a web page or similar. Each element of a line is
	 * delimited by a single hyphen.
	 */
	public void printWeb() {
		System.out.println(name);
		Team[] t = teamsToArray();
		// Sorts teams into non-descending order by wins
		for (int i = 0; i < t.length; i++) {
			for (int j = i; j < t.length; j++) {
				if (t[i].getPoints() - (t[j].getPoints()) < 0) {
					Team temp = t[i];
					t[i] = t[j];
					t[j] = temp;
				}
			}
		}
		for (Team team : t) {
			System.out.println(team.getName() + "-" + team.getPoints() + "-"
					+ team.getGamesPlayed() + "-" + team.isEliminated());
		}
		System.out.println();
	}

	// loop through every game played in the current division,
	// check if date is less than/equal to current date,
	// if not unplay match
	public void updateMatches(DateTime dt) {
		for (Match m : getFixtures()) {
			if (m.getDateTime().before(dt)) {
				m.playMatch();
			} else {
				m.unplayMatch();
			}
		}
		for (Team t : getTeams()) {
			t.setEliminated(false);
			t.setTrivial(false);
			t.getEliminatedBy().clear();
			(new Algorithm(this)).isEliminated(t);
		}
	}

}
