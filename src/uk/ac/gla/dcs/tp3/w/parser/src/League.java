import java.util.ArrayList;

/**
 * This class has two members: an array of teams; and an array of fixtures. The
 * primary purpose of this class is to maintain, and keep track of, progress
 * through a given sports league.
 * 
 * @author Team W
 * @version 1.0
 */
public class League {
	//overall standings 
	private ArrayList<Team> teams;
	//overall fixtures
	private ArrayList<Match> fixtures;

	/**
	 * No parameter constructor. Sets both instance variables to null.
	 */
	public League() {
		this(null, null);
		teams = new ArrayList<Team>();
		fixtures = new ArrayList<Match>();
	}

	/**
	 * A typical constructor, each instance variable has an associated
	 * parameter.
	 * 
	 * @param t
	 *            The array of teams in this league.
	 * @param m
	 *            The array of matches in this league.
	 */
	public League(ArrayList<Team> t, ArrayList<Match> m) {
		teams = t;
		fixtures = m;
		teams = new ArrayList<Team>();
		fixtures = new ArrayList<Match>();
	}

	/**
	 * Get the list of teams in the sports league
	 * 
	 * @return Team array of participating teams
	 */
	public ArrayList<Team> getTeams() {
 		return teams;
	}

	/**
	 * Set the list of teams in the sports league
	 * 
	 * @param teams
	 *            Team array of participating teams
	 */
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	/**
	 * Get the list of matches in the sports league
	 * 
	 * @return Match array of all matches
	 */
	public ArrayList<Match> getFixtures() {
		return fixtures;
	}

	/**
	 * Set the list of matches in the sports league
	 * 
	 * @param fixtures
	 *            Match array of all matches
	 */
	public void setFixtures(ArrayList<Match> fixtures) {
		this.fixtures = fixtures;
	}

	/**
	 * Adds given match into the fixture array if not currently in the array.
	 * Also, if the match has not been played, calls the appropriate Team method
	 * to add the match to the upcoming match array for the associated teams.
	 * 
	 * @param m
	 *            The match to add to the fixture list.
	 */
	public void addFixture(Match m){
		if(!fixtures.contains(m)){
			fixtures.add(m);
		}
	}

	/**
	 * Adds given team into the team array if not currently in the array.
	 * 
	 * @param t
	 *            The team to be added to the team array.
	 */
	public void addTeam(Team t) {
		teams.add(t);
	}
	
	public boolean isMember(Team t){
		if(!teams.isEmpty()){
			if(teams.contains(t))
				return true;
		}
		return false;
	}
	
	public String toString(){
		return String.format("%s", teams);
	}

}
