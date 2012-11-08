package uk.ac.gla.dcs.tp3.w.league;

/**
 * This class represents a single team in a sport. The class holds the name,
 * points, number of games played in a given league, upcoming matches, and a
 * state saying whether or not the team has a chance of winning.
 * 
 * @author Team W
 * @version 1.0
 */
public class Team {
	private String name;
	private int points;
	private int gamesPlayed;
	private boolean eliminated;
	private Match[] upcomingMatches;
	private Team[] eliminatedBy;

	/**
	 * No parameter constructor. Sets all instance variables to null, -1, or
	 * false.
	 */
	public Team() {
		this(null, -1, -1, false, null, null);
	}

	/**
	 * A typical constructor to create a Team object.
	 * 
	 * @param s
	 *            Name of the team
	 * @param p
	 *            Initial number of points for the team
	 * @param g
	 *            Number of games the team has played
	 * @param e
	 *            Boolean to state if the team has no chance of winning the
	 *            league
	 * @param um
	 *            An array of Matches the team is still to play.
	 * @param t
	 *            The array of teams responsible for eliminating this team.
	 */
	public Team(String s, int p, int g, boolean e, Match[] um, Team[] t) {
		name = s;
		points = p;
		gamesPlayed = g;
		eliminated = e;
		upcomingMatches = um;
		eliminatedBy = t;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public boolean isEliminated() {
		return eliminated;
	}

	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}

	public Match[] getUpcomingMatches() {
		return upcomingMatches;
	}

	public void setUpcomingMatches(Match[] upcomingMatches) {
		this.upcomingMatches = upcomingMatches;
	}

	public Team[] getEliminatedBy() {
		return eliminatedBy;
	}

	public void setEliminatedBy(Team[] eliminatedBy) {
		this.eliminatedBy = eliminatedBy;
	}

	/**
	 * Adds a match to the list of games a team is still to play
	 * 
	 * @param m
	 *            The match the team is still to play
	 */
	public void addUpcomingMatch(Match m) {
		// TODO
	}

	/**
	 * Removes a match from the upcoming matches array.
	 * 
	 * @param m
	 *            The match to be deleted
	 * @return Returns the match, or null if unsuccessful (if the match doesn't
	 *         exist in the array)
	 */
	public Match removeUpcomingMatch(Match m) {
		// TODO
		return null;
	}

}
