package uk.ac.gla.dcs.tp3.w.tests;

import java.util.HashMap;

import org.junit.Test;

import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.*;

public class ParserTest {

	private static Parser p;

	public ParserTest() {
	}

	@Test
	public void testEmptyUpcomingMatches() {
		p = new Parser();
		p.parse("");
		HashMap<String, Division> map = p.getDivisions();
		for (Division d : map.values()) {
			for (Team t : d.getTeams()) {
				assert (t.getUpcomingMatches().size() == 0);
			}
		}
	}

}
