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

	/**
	 * Get the name of the team
	 * 
	 * @return String representing the Team name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the team
	 * 
	 * @param name
	 *            String representing the Team name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the number of points a team has.
	 * 
	 * For baseball this is synonymous with the number of wins a team has since
	 * a team gains 1 point per win.
	 * 
	 * @return int representing the number of points a team has
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Set the number of points a team has.
	 * 
	 * For baseball this is synonymous with the number of wins a team has since
	 * a team gains 1 point per win.
	 * 
	 * @param points
	 *            int representing the number of points a team has
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Get the number of games a team has played.
	 * 
	 * @return int representing the number of games a team has played
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * Set the number of games a team has played.
	 * 
	 * @param gamesPlayed
	 *            representing the number of games a team has played
	 */
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	/**
	 * Check to see if the team has been eliminated from the sports league
	 * 
	 * @return boolean representing whether or not a team has been eliminated.
	 */
	public boolean isEliminated() {
		return eliminated;
	}

	/**
	 * Set whether or not the team has been eliminated from the sports league
	 * 
	 * @param eliminated
	 *            boolean representing whether or not a team has been
	 *            eliminated.
	 */
	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}

	/**
	 * Get the list of matches a team still has to play.
	 * 
	 * @return Match array representing all of the unplayed matches by the team
	 */
	public Match[] getUpcomingMatches() {
		return upcomingMatches;
	}

	/**
	 * Set the list of matches a team still has to play
	 * 
	 * @param upcomingMatches
	 *            Match array represent all of the unplayed matches by the team
	 */
	public void setUpcomingMatches(Match[] upcomingMatches) {
		this.upcomingMatches = upcomingMatches;
	}

	/**
	 * Get the list of teams which were responsible for the elimination of this
	 * team from the sports league
	 * 
	 * @return Team array representing all of the teams responsible for
	 *         eliminating this team.
	 */
	public Team[] getEliminatedBy() {
		return eliminatedBy;
	}

	/**
	 * Set the list of teams which were responsible for the elimination of this
	 * team from the sports league
	 * 
	 * @param eliminatedBy
	 *            Team array representing all of the teams responsible for
	 *            eliminating this team.
	 */
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

	/**
	 * Returns a boolean stating whether or not the two teams are the same team.
	 * 
	 * @param o
	 *            The object to be compared with the calling team
	 * @return boolean stating the truth value of the comparison
	 */
	public boolean equals(Object o) {
		if (o instanceof Team) {
			Team other = (Team) o;
			return (other == null) ? false : (this.name == other.getName());
		}
		return false;
	}

}
