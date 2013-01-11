package uk.ac.gla.dcs.tp3.w.algorithm;

import uk.ac.gla.dcs.tp3.w.league.Team;

/**
 * This class extends Vertex which represents a Pair Node in a Network graph.
 * Both parts of the pair is a Team in the same division.
 * 
 * @author Team W
 * @version 1.0
 */
public class PairVertex extends Vertex {

	private Team teamA;
	private Team teamB;

	/**
	 * Basic no parameter constructor.
	 */
	public PairVertex() {
		this(null, null, -1);
	}

	/**
	 * Creates a new pair vertex with associated index.
	 * 
	 * @param a
	 *            One team of the pair
	 * @param b
	 *            The other team of the pair
	 * @param n
	 *            Index in the array of vertices
	 */
	public PairVertex(Team a, Team b, int n) {
		super(n);
		teamA = a;
		teamB = b;
	}

	/**
	 * Return one element of the team
	 * 
	 * @return Return team A of the pair
	 */
	public Team getTeamA() {
		return teamA;
	}

	/**
	 * Set new team A of the pair
	 * 
	 * @param teamA
	 *            new team A for the pair
	 */
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	/**
	 * Return one element of the team
	 * 
	 * @return Return team B of the pair
	 */
	public Team getTeamB() {
		return teamB;
	}

	/**
	 * Set new team B of the pair
	 * 
	 * @param teamB
	 *            new team B for the pair
	 */
	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}

}
