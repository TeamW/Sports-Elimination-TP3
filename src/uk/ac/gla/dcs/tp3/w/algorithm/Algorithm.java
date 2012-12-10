package uk.ac.gla.dcs.tp3.w.algorithm;

import java.util.Stack;

import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class Algorithm {

	private Graph g;
	private Division d;
	@SuppressWarnings("unused")
	private static boolean verbose = false;

	public Algorithm() {
		this(null);
	}

	public Algorithm(Division div) {
		g = null;
		d = div;
	}

	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	public Division getD() {
		return d;
	}

	public void setD(Division d) {
		this.d = d;
	}

	public boolean isEliminated(Team t) {
		// If the team is already known to be eliminated, just return true.
		return t.isEliminated() ? true : fordFulkerson(t);
	}

	public void setVerbose() {
		verbose = true;
	}

	private boolean fordFulkerson(Team t) {
		// Create initial graph
		g = new Graph(d, t);
		// For each edge in the graph g, set the flow to be 0.
		for (Vertex v : g.getV())
			for (AdjListNode n : v.getAdjList())
				n.setFlow(0);
		// Cap states the maximum possible flow away from source.
		int cap = 0;
		for (AdjListNode a : g.getSource().getAdjList())
			cap += a.getCapacity();
		// Create initial residual graph for algorithm.
		ResidualGraph residual = new ResidualGraph(g);
		// Path will store the residual path (if it exists)
		Path path;
		// Algorithm continues while a residual path exists
		while ((path = residualPath(residual)) != null) {
			// Reduce cap by the amount of flow that can be added in this
			// iteration.
			cap -= path.getCapacity();
			// For each node in the path, add/remove the path's capacity from
			// the edges flow. Add to forward edges, remove from backward edges.
			int[] pathnodes = path.getPath();
			for (int j = 1; j < pathnodes.length; j++) {
				int i = j - 1;
				if (pathnodes[i] < pathnodes[j]) {
					for (AdjListNode a : g.getV()[pathnodes[i]].getAdjList())
						// Forward edge, add flow.
						if (a.getVertex().getIndex() == pathnodes[j])
							a.setFlow(a.getFlow() + path.getCapacity());
				} else {
					for (AdjListNode a : g.getV()[pathnodes[i]].getAdjList())
						// Backward edge, remove flow.
						if (a.getVertex().getIndex() == pathnodes[j])
							a.setFlow(a.getFlow() - path.getCapacity());
				}
			}
			// Update residual graph based on new original graph's flow data.
			residual = new ResidualGraph(g);
		}

		// Extension: Max Flow-Min Cut Theorem.
		// Overview:
		// The value of a maximum flow is equal to the capacity of a minimum
		// cut.
		// Obtain two sets of vertices, A and B. The source is a member of A.
		// The sink is a member of B.
		// The team nodes that are in B are the teams responsible for the
		// elimination of team t. These
		// teams for the certificate of elimination.

		// If final flow of graph is saturating, team has not been eliminated,
		// return false.
		// Otherwise, team has been eliminated, return true.
		return cap != 0;
	}

	private static Path residualPath(ResidualGraph g) {
		// Perform BFS from source on graph
		g.bfs();
		// Since we'll work out a path from sink to source, it will be added to a
		// stack then popped until empty to store path in correct direction.
		Stack<Integer> backPath = new Stack<Integer>();
		// Use matrix representation of edge capacities to speed up this method.
		int[][] matrix = g.getMatrix();
		int next, current, capacity = Integer.MAX_VALUE;
		// Start at sink, try to work back up to source (vertex 0)
		Vertex v[] = g.getV();
		next = current = g.getSink().getIndex();
		// Until we either get stuck, or get to the source, keep going.
		while (true) {
			// If we're stuck outside the source, we're finished.
			if (v[next].getPred() == next && next != 0)
				return null;
			// Add next node to the path.
			backPath.add(next);
			// Ensure current path capacity is at it's lowest possible value
			if (current != next && matrix[next][current] < capacity)
				capacity = matrix[next][current];
			// If we're at the source, one path has been found.
			if (next == 0)
				break;
			// Bump up one step up the path
			current = next;
			next = v[next].getPred();
		}
		// Pop everything from the stack to make the path in source->sink order.
		int[] path = new int[backPath.size()];
		for (int i = 0; i < path.length; i++)
			path[i] = backPath.pop();
		return new Path(path, capacity);
	}
	
	//Currently returns the value of the highest eliminated team.
	//Note, this value corresponds to teams being ordered in non-
	//descending order by wins+gamesRemaning.
	public void updateDivisionElim(Division d){
		Team[] teams = (Team[]) d.getTeams().toArray();
		for(int i = 0; i<teams.length; i++){
			for(int j = 0; j<teams.length; i ++){
				if (teams[i].compareTo(teams[j])>0){
					Team temp = teams[i];
					teams[i] = teams[j];
					teams[j] = temp;
				}
			}
		}
		int lastElim = binaryDetermine(teams,0,teams.length,-1);
		for (int i = 0; i<=lastElim;i++){
			teams[i].setEliminated(true);
		}
	}
	
	private int binaryDetermine(Team[] T, int s, int e, int highestElim){
		if (e<s||s>e||s<0||e>T.length) return highestElim;
		int mid = (s+e)/2;
		if(fordFulkerson(T[mid])){
			highestElim = mid;
			return binaryDetermine(T,mid+1,e,highestElim);
		}
		else
			return binaryDetermine(T,s,mid-1,highestElim);		
	}
}
