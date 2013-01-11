package uk.ac.gla.dcs.tp3.w.algorithm;

import uk.ac.gla.dcs.tp3.w.league.Team;

/**
 * This class extends Vertex which represents a Team Node in a Network graph.
 * The Team vertex holds a single team variable only.
 * 
 * @author Team W
 * @version 1.0
 */
public class TeamVertex extends Vertex {

	private Team team;

	/**
	 * No parameter constructor.
	 */
	public TeamVertex() {
		this(null, -1);
	}

	/**
	 * Creates a new team vertex based on the given team and array index.
	 * 
	 * @param t
	 *            Team variable represented by the vertex
	 * @param n
	 *            int value representing where in the array of vertices this
	 *            vertex will exist.
	 */
	public TeamVertex(Team t, int n) {
		super(n);
		setTeam(t);
	}

	/**
	 * Get the team represented by the vertex.
	 * 
	 * @return Team vertex represented by the vertex
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Set the team represented by the vertex.
	 * 
	 * @param Team
	 *            vertex represented by the vertex
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

}
