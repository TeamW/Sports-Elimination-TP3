/**
 * 
 */
package uk.ac.gla.dcs.tp3.w.algorithm;

import uk.ac.gla.dcs.tp3.w.league.Team;

/**
 * @author gordonreid
 * 
 */
public class TeamVertex extends Vertex {

	private Team team;

	public TeamVertex() {
		this(null, -1);
	}

	public TeamVertex(Team t, int n) {
		super(n);
		setTeam(t);
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
