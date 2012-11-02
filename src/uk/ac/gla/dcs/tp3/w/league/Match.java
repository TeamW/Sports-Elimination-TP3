package uk.ac.gla.dcs.tp3.w.league;

import java.util.Date;

public class Match {

	private Team homeTeam;
	private Team awayTeam;
	private int homeScore;
	private int awayScore;
	private Date date;
	private boolean played;

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

	public Team getWinner() {
		return (homeScore > awayScore) ? homeTeam : awayTeam;
	}

	public void updatePointsAndPlayGame() {
		// Only execute this method once.
		if (played) {
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
