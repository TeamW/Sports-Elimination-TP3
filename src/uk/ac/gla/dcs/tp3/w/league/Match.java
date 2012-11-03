package uk.ac.gla.dcs.tp3.w.league;

import java.util.Date;

/**
 * This class has many members. The class holds the information, and result of,
 * a match between two teams. This is an element of the fixture list which is
 * used by both the UI and algorithm.
 * 
 * @author Team W
 * @version 1.0
 */
public class Match {

	private Team homeTeam;
	private Team awayTeam;
	private int homeScore;
	private int awayScore;
	private Date date;
	private boolean played;

	/**
	 * No parameter constructor. Sets all instance variables to null, -1, or
	 * false.
	 */
	public Match() {
		this(null, null, -1, -1, null, false);
	}

	/**
	 * A typical constructor to create a match object.
	 * 
	 * @param h
	 *            The home team
	 * @param a
	 *            The away team
	 * @param hs
	 *            The home team's score
	 * @param as
	 *            The away team's score
	 * @param d
	 *            The date the match is played
	 * @param b
	 *            A boolean to show if the match has been played or not.
	 */
	public Match(Team h, Team a, int hs, int as, Date d, boolean b) {
		homeTeam = h;
		awayTeam = a;
		homeScore = hs;
		awayScore = as;
		date = d;
		played = b;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	/**
	 * Works out which team won the match and returns a reference to the winning
	 * team. If the game has not been played yet, null is returned.
	 * 
	 * @return A reference to the team with the higher score.
	 */
	public Team getWinner() {
		if (played) {
			return (homeScore > awayScore) ? homeTeam : awayTeam;
		} else {
			return null;
		}
	}

	/**
	 * If the match has just been played, find out which team won. Add a point
	 * for the winning team and increment the total number of games played by
	 * both teams then remove the reference to the upcoming match arrays.
	 */
	public void updatePointsAndPlayGame() {
		// Only execute this method once. Also ensure scores have been set.
		if (played || homeScore == -1 || awayScore == -1) {
			return;
		}
		played = true;
		// t is the winner of the match, s is the loser of the match.
		Team t = getWinner();
		Team s = ((t == homeTeam) ? awayTeam : homeTeam);
		// t gets a point, s does not. Increment number of games played and
		// remove associated match for both teams.
		t.setPoints(t.getPoints() + 1);
		t.setGamesPlayed(t.getGamesPlayed() + 1);
		t.removeUpcomingMatch(this);
		s.setGamesPlayed(s.getGamesPlayed() + 1);
		s.removeUpcomingMatch(this);
	}

}
