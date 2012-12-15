package uk.ac.gla.dcs.tp3.w.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.gla.dcs.tp3.w.algorithm.*;
import uk.ac.gla.dcs.tp3.w.league.*;

public class AlgorithmTest {

	private static Algorithm alg;

	@Before
	public void before() {
		alg = new Algorithm();
	}

	@After
	public void after() {
		alg = new Algorithm();
	}

	/*
	 * ATLANTA TESTS
	 */

	@Test
	public void testWayneAtlantaElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			t.setEliminated(alg.isEliminated(t));
			if (t.getName().equalsIgnoreCase("Atlanta")) {
				assertEquals(t.isEliminated(), false);
				break;
			}
		}
	}

	@Test
	public void testWayneAtlantaEliminationCondition() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Atlanta")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
					flow += n.getFlow();
				}
				assertEquals(capacity, flow);
				break;
			}
		}
	}

	@Test
	public void testWayneAtlantaCapacityValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Atlanta")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
				}
				assertEquals(4, capacity);
				break;
			}
		}
	}

	@Test
	public void testWayneAtlantaFlowValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Atlanta")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					flow += n.getFlow();
				}
				assertEquals(4, flow);
				break;
			}
		}
	}

	@Test
	public void testWayneAtlantaCertificateOfElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Atlanta")) {
				t.setEliminated(alg.isEliminated(t));
				Object[] teams = t.getEliminatedBy().toArray();
				Object[] result = {};
				assertArrayEquals(teams, result);
			}
		}
	}

	/*
	 * PHILADELPHIA TESTS
	 */

	@Test
	public void testWaynePhiladelphiaElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			t.setEliminated(alg.isEliminated(t));
			if (t.getName().equalsIgnoreCase("Philadelphia")) {
				assertEquals(t.isEliminated(), true);
				break;
			}
		}
	}

	@Test
	public void testWaynePhiladelphiaEliminationCondition() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Philadelphia")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
					flow += n.getFlow();
				}
				assertEquals(false, capacity == flow);
				break;
			}
		}
	}

	@Test
	public void testWaynePhiladelphiaCapacityValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Philadelphia")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
				}
				assertEquals(8, capacity);
				break;
			}
		}
	}

	@Test
	public void testWaynePhiladelphiaFlowValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Philadelphia")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					flow += n.getFlow();
				}
				assertEquals(7, flow);
				break;
			}
		}
	}

	@Test
	public void testWaynePhiladelphiaCertificateOfElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Philadelphia")) {
				t.setEliminated(alg.isEliminated(t));
				Object[] teams = t.getEliminatedBy().toArray();
				Object[] result = { new Team("New York"), new Team("Atlanta") };
				assertArrayEquals(teams, result);
			}
		}
	}

	/*
	 * NEW YORK TESTS
	 */

	@Test
	public void testWayneNewYorkElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			t.setEliminated(alg.isEliminated(t));
			if (t.getName().equalsIgnoreCase("New York")) {
				assertEquals(t.isEliminated(), false);
				break;
			}
		}
	}

	@Test
	public void testWayneNewYorkEliminationCondition() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("New York")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
					flow += n.getFlow();
				}
				assertEquals(capacity, flow);
				break;
			}
		}
	}

	@Test
	public void testWayneNewYorkCapacityValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("New York")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
				}
				assertEquals(5, capacity);
				break;
			}
		}
	}
	
	@Test
	public void testWayneNewYorkCertificateOfElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("New York")) {
				t.setEliminated(alg.isEliminated(t));
				Object[] teams = t.getEliminatedBy().toArray();
				Object[] result = {};
				assertArrayEquals(teams, result);
			}
		}
	}

	@Test
	public void testWayneNewYorkFlowValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("NewYork")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					flow += n.getFlow();
				}
				assertEquals(5, flow);
				break;
			}
		}
	}

	/*
	 * MONTREAL TESTS
	 */

	@Test
	public void testWayneMontrealElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			t.setEliminated(alg.isEliminated(t));
			if (t.getName().equalsIgnoreCase("Montreal")) {
				assertEquals(t.isEliminated(), true);
				break;
			}
		}
	}

	@Test
	public void testWayneMontrealEliminationCondition() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Montreal")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
					flow += n.getFlow();
				}
				assertEquals(false, capacity == flow);
				break;
			}
		}
	}

	@Test
	public void testWayneMontrealCapacityValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Montreal")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int capacity = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					capacity += n.getCapacity();
				}
				assertEquals(7, capacity);
				break;
			}
		}
	}

	@Test
	public void testWayneMontrealFlowValue() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Montreal")) {
				t.setEliminated(alg.isEliminated(t));
				Graph g = alg.getG();
				int flow = 0;
				for (AdjListNode n : g.getV()[0].getAdjList()) {
					flow += n.getFlow();
				}
				assertEquals(3, flow);
				break;
			}
		}
	}

	@Test
	public void testWayneMontrealCertificateOfElimination() {
		generateWayne();
		for (Team t : alg.getD().getTeams()) {
			if (t.getName().equalsIgnoreCase("Montreal")) {
				t.setEliminated(alg.isEliminated(t));
				Object[] teams = t.getEliminatedBy().toArray();
				Object[] result = { new Team("New York"),
						new Team("Philadelphia"), new Team("Atlanta") };
				assertArrayEquals(teams, result);
			}
		}
	}

	/*
	 * STATIC METHOD REQUIRED FOR ALL OF ABOVE TESTS
	 */
	private static void generateWayne() {
		Team atlanta = new Team();
		atlanta.setName("Atlanta");
		atlanta.setGamesPlayed(170 - 8);
		atlanta.setPoints(83);

		Team philadelphia = new Team();
		philadelphia.setName("Philadelphia");
		philadelphia.setGamesPlayed(170 - 4);
		philadelphia.setPoints(79);

		Team newYork = new Team();
		newYork.setName("New York");
		newYork.setGamesPlayed(170 - 7);
		newYork.setPoints(78);

		Team montreal = new Team();
		montreal.setName("Montreal");
		montreal.setGamesPlayed(170 - 5);
		montreal.setPoints(76);

		ArrayList<Match> atlantaMatches = new ArrayList<Match>();
		ArrayList<Match> philadelphiaMatches = new ArrayList<Match>();
		ArrayList<Match> newYorkMatches = new ArrayList<Match>();
		ArrayList<Match> montrealMatches = new ArrayList<Match>();
		ArrayList<Match> allMatches = new ArrayList<Match>();

		Match atlVphil = new Match();
		atlVphil.setHomeTeam(atlanta);
		atlVphil.setAwayTeam(philadelphia);
		atlantaMatches.add(atlVphil);
		philadelphiaMatches.add(atlVphil);
		allMatches.add(atlVphil);

		Match atlVny = new Match();
		atlVny.setHomeTeam(atlanta);
		atlVny.setAwayTeam(newYork);
		for (int i = 0; i < 6; i++) {
			atlantaMatches.add(atlVny);
			newYorkMatches.add(atlVny);
			allMatches.add(atlVny);
		}

		Match atlVmon = new Match();
		atlVmon.setHomeTeam(atlanta);
		atlVmon.setAwayTeam(montreal);
		atlantaMatches.add(atlVmon);
		montrealMatches.add(atlVmon);
		allMatches.add(atlVmon);

		Match philVmon = new Match();
		philVmon.setHomeTeam(philadelphia);
		philVmon.setAwayTeam(montreal);
		for (int i = 0; i < 3; i++) {
			philadelphiaMatches.add(philVmon);
			montrealMatches.add(philVmon);
			allMatches.add(philVmon);
		}

		Match nyVmon = new Match();
		nyVmon.setHomeTeam(newYork);
		nyVmon.setAwayTeam(montreal);
		newYorkMatches.add(nyVmon);
		montrealMatches.add(nyVmon);
		allMatches.add(nyVmon);

		atlanta.setUpcomingMatches(atlantaMatches);
		philadelphia.setUpcomingMatches(philadelphiaMatches);
		newYork.setUpcomingMatches(newYorkMatches);
		montreal.setUpcomingMatches(montrealMatches);

		ArrayList<Team> teams = new ArrayList<Team>();
		teams.add(atlanta);
		teams.add(philadelphia);
		teams.add(newYork);
		teams.add(montreal);

		Division d = new Division(teams, allMatches);
		alg = new Algorithm(d);
	}

}
