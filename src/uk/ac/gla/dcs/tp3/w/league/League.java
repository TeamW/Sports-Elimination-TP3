package uk.ac.gla.dcs.tp3.w.league;

/**
 * This class has two members: an array of teams; and an array of fixtures. The
 * primary purpose of this class is to maintain, and keep track of, progress
 * through a given sports league.
 * 
 * @author Team W
 * @version 1.0
 */
public class League {

	private Team[] teams;
	private Match[] fixtures;

	/**
	 * No parameter constructor. Sets both instance variables to null.
	 */
	public League() {
		this(null, null);
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
	public League(Team[] t, Match[] m) {
		teams = t;
		fixtures = m;
	}

	public Team[] getTeams() {
		return teams;
	}

	public void setTeams(Team[] teams) {
		this.teams = teams;
	}

	public Match[] getFixtures() {
		return fixtures;
	}

	public void setFixtures(Match[] fixtures) {
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
		// TODO
	}

	/**
	 * Adds given team into the team array if not currently in the array.
	 * 
	 * @param t
	 *            The team to be added to the team array.
	 */
	public void addTeam(Team t) {
		// TODO
	}

}
