package uk.ac.gla.dcs.tp3.w.gen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import uk.ac.gla.dcs.tp3.w.league.DateTime;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;
import uk.ac.gla.dcs.tp3.w.parser.Parser;

public class GenerateLeague {
	private String fileName = null;
	private int gamesPerTeam = 180;
	private boolean verbose = false;
	
	public GenerateLeague(/*String fileName*/) {
		/*this.fileName = fileName;
		if(!this.createFile()){
			System.err.println("File could not be created, aborting");
			return;
		}*/
	}
	public void generate(){
		Parser p = new Parser();
		p.generateStandardDivisionInfo();
		int teamSize, gamesBetweenTeams, i, j, k;
		int scoreA, scoreB, index = 0;
		Random r = new Random();
		DateTime date = new DateTime();
		ArrayList<String> fixtures = new ArrayList<String>();
		for(Division d: p.getDivisions().values()){
			System.out.println("New Division: " + d.getName());
			ArrayList<Team> teams = d.getTeams();
			teamSize = teams.size();
			gamesBetweenTeams = gamesPerTeam/teamSize;
			System.out.println("Games Between Teams: " + gamesBetweenTeams);
			for(i=0;i<teamSize;i++){
				for(j=i+1;j<teamSize;j++){
					for(k=0;k<gamesBetweenTeams;k++){
						scoreA = r.nextInt(10);
						scoreB = r.nextInt(10);
						if (scoreA==scoreB) scoreB=(scoreB+1)%10;
						if(!fixtures.isEmpty())
						index = r.nextInt(fixtures.size());
						if(verbose)	System.out.println(teams.get(i) + " - " + teams.get(j) + " - " + scoreA +":"+scoreB + " - " + index);
					}
				}
			}
		}
	}

	private boolean createFile() {
		if(fileName==null) return false;
		File F = new File(System.getProperty("user.dir") + "/" + fileName
				+ ".txt");
		try {
			return F.createNewFile();
		} catch (IOException IOE) {
			IOE.printStackTrace();
			return false;
		}
		
	}
	
	public void verbose(){
		verbose=true;
	}
	
	public static void main(String[] args){
		GenerateLeague gn = new GenerateLeague();
		gn.verbose();
		gn.generate();
	}

}
