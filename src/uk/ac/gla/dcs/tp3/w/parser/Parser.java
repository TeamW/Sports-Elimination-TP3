package uk.ac.gla.dcs.tp3.w.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import uk.ac.gla.dcs.tp3.w.league.*;

/**
 * @author James & Kris
 * 
 */
public class Parser {

	Division nationalEast = new Division();
	Division nationalCentral = new Division();
	Division nationalWest = new Division();

	Division americanEast = new Division();
	Division americanCentral = new Division();
	Division americanWest = new Division();

	public Parser(File f) {
		// ArrayList<Division> leagueEachDay = new ArrayList<Division>();
		// //array of
		// days in season each with seperate state
		Division theLeague = new Division(); // -- edit

		boolean postponedCheck = false;
		String thisLine = "", date = "";
		Date d = new Date();
		Scanner fileScanner, lineScanner;
		int score1 = 0, score2 = 0;

		File source = f;
		// File source = new File(System.getProperty("user.dir")
		// + "/src/uk/ac/gla/dcs/tp3/w/parser/baseballSource.txt");

		try {
			fileScanner = new Scanner(source);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		thisLine = fileScanner.nextLine();

		// leagueEachDay.add(new Division()); //date emulation (adding a day
		// and manipulating it's state below)
		while (fileScanner.hasNextLine() || thisLine.isEmpty()) {
			/** Setting the date */
			if (thisLine.isEmpty()) {
				date = fileScanner.nextLine();
				lineScanner = new Scanner(date);
				d.setDate(lineScanner.nextInt()); // setting day
				String m = lineScanner.next();
				d.setMonth(getMonth(m) - 1); // month converted to a number
				// and stored
				d.setYear(lineScanner.nextInt()); // year
			}

			/** Checking to see if we're on the same day */

			thisLine = fileScanner.nextLine(); // get each line one at a
			// time(within a day)
			if (thisLine.isEmpty()) {
				continue;
			}
			lineScanner = new Scanner(thisLine); // start new scanner to
			// scan particular line

			/** parsing out information from a particular line */
			String time = "", team1Name = "", team2Name = "", scoreOne = "", scoreTwo = "";
			time = lineScanner.next(); // store time
			boolean hyphenCheck = false, atScore2 = false;

			// loop through characters in the line starting at first team
			// name
			for (int i = 6; i < thisLine.length(); i++) {
				char c = thisLine.charAt(i);

				// time here

				// check & add char to team 1's name
				if (c != '-' && hyphenCheck == false) {
					team1Name += c;
				}
				// check and add char to team 2's name if past hyphen in
				// source
				if (hyphenCheck
						&& (c != '0' && c != '1' && c != '2' && c != '3'
								&& c != '4' && c != '5' && c != '6' && c != '7'
								&& c != '8' && c != '9') && c != '\t'
						&& c != ':' && c != '$') {
					team2Name += c;
				}
				// lets it know we're past the hyphen
				if (c == '-') {
					hyphenCheck = true;
					i++;
				}
				// moved onto score of both teams
				if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
						|| c == '5' || c == '6' || c == '7' || c == '8'
						|| c == '9' || c == ':' || c == '$') {
					/** if it hits a colon, working on team 2's score */
					if (c == ':') {
						atScore2 = true;
						continue;
					} else if (c == '$') {
						postponedCheck = true;
					}

					/** setting score's for teams 1 and 2 */
					if (atScore2) {
						scoreTwo += c;
					} else {
						scoreOne += c;
					}
				}
			}

			// creating 2 temporary
			// team objects
			// to store
			// parsed info
			Team t1 = new Team(removeSpaces(team1Name));
			Team t2 = new Team(removeSpaces(team2Name)); // handle null
			// pointer error

			// convert score to integers if the match was not postponed
			if (!postponedCheck) {
				score1 = Integer.parseInt(scoreOne);
				score2 = Integer.parseInt(scoreTwo);

				/**
				 * allocating points to the team which won the match + another
				 * if statement surrounding this to check if we're before the
				 * date of this match occurring with the emulated date, if so
				 * don't calculate scores
				 * */
				if (score1 > score2) {
					for (Team t : theLeague.getTeams()) {
						if (t.equals(t1)) {
							t.setPoints(t.getPoints() + 1);
							break;
						}
					}
				} else {
					for (Team t : theLeague.getTeams()) {
						if (t.equals(t2)) {
							t.setPoints(t.getPoints() + 1);
							break;
						}
					}
				}
			}
			postponedCheck = false;

			/** Checking if team exists in league or if not, add it */
			if (!theLeague.isMember(t1)) {
				theLeague.addTeam(new Team(removeSpaces(team1Name)));
			}
			if (!theLeague.isMember(t2)) {
				theLeague.addTeam(new Team(removeSpaces(team2Name)));
			}

			/** Getting the Team objects out of main storage */
			Team temp1 = null, temp2 = null;
			for (Team t : theLeague.getTeams()) {
				if (t.equals(t1)) {
					temp1 = t;
				}
			}
			for (Team t : theLeague.getTeams()) {
				if (t.equals(t2)) {
					temp2 = t;
				}
			}

			/** and then creating the match object with them + scores */
			Match match = new Match(temp1, temp2, score1, score2, d, false);
			theLeague.addFixture(match);

			/** add the match to the upcoming matches of both teams */
			for (Team t : theLeague.getTeams()) {
				if (t.equals(t1)) {
					t.addUpcomingMatch(match);
					break;
				}
			}
			for (Team t : theLeague.getTeams()) {
				if (t.equals(t2)) {
					t.addUpcomingMatch(match);
					break;
				}
			}

		}// end of while loop

		// check file has been parsed properly
		// System.out.println(theLeague);

		// split up theLeague into 6 smaller divisions

		for (int x = 0; x < theLeague.getTeams().size(); x++) {

			String tt = removeSpaces(theLeague.getTeams().get(x).getName());

			String t = tt.trim();

			Team testT = theLeague.getTeams().get(x);

			// Set American Division : East
			if (t.equalsIgnoreCase("Baltimore Orioles")
					|| t.equalsIgnoreCase("Boston Red Sox")
					|| t.equalsIgnoreCase("New York Yankees")
					|| t.equalsIgnoreCase("Tampa Bay Rays")
					|| t.equalsIgnoreCase("Toronto Blue Jays")
					&& !americanEast.isMember(testT)) {
				americanEast.addTeam(testT);
			}

			// Set American Division : Central
			if ((t.equalsIgnoreCase("Chicago White Sox")
					|| t.equalsIgnoreCase("Cleveland Indians")
					|| t.equalsIgnoreCase("Detroit Tigers")
					|| t.equalsIgnoreCase("Kansas City Royals") || t
						.equalsIgnoreCase("Minnesota Twins"))
					&& !americanCentral.isMember(testT)) {
				americanCentral.addTeam(testT);
			}

			// Set American Division : West
			if ((t.equalsIgnoreCase("Seattle Mariners")
					|| t.equalsIgnoreCase("Texas Rangers")
					|| t.equalsIgnoreCase("Houston Astros")
					|| t.equalsIgnoreCase("Los Angeles Angels") || t
						.equalsIgnoreCase("Oakland Athletics"))
					&& !americanWest.isMember(testT)) {
				americanWest.addTeam(testT);
			}

			// Set National Division : East
			if ((t.equalsIgnoreCase("Atlanta Braves")
					|| t.equalsIgnoreCase("Miami Marlins")
					|| t.equalsIgnoreCase("New York Mets")
					|| t.equalsIgnoreCase("Philadelphia Phillies") || t
						.equalsIgnoreCase("Washington Nationals"))
					&& !nationalEast.isMember(testT)) {
				nationalEast.addTeam(testT);
			}

			// Set National Division : Central
			if ((t.equalsIgnoreCase("Chicago Cubs")
					|| t.equalsIgnoreCase("Cincinnati Reds")
					|| t.equalsIgnoreCase("Milwaukee Brewers")
					|| t.equalsIgnoreCase("Pittsburgh Pirates") || t
						.equalsIgnoreCase("St.Louis Cardinals"))
					&& !nationalCentral.isMember(testT)) {
				nationalCentral.addTeam(testT);
			}

			// Set National Division : West
			if ((t.equalsIgnoreCase("Arizona Diamondbacks")
					|| t.equalsIgnoreCase("Colorado Rockies")
					|| t.equalsIgnoreCase("San Francisco Giants")
					|| t.equalsIgnoreCase("Los Angeles Dodgers") || t
						.equalsIgnoreCase("San Diego Padres"))
					&& !nationalWest.isMember(testT)) {
				nationalWest.addTeam(testT);
			}

			// System.out.println(americanEast);
			// System.out.println(americanCentral);
			// System.out.println(americanWest);

			// System.out.println(nationalEast);
			// System.out.println(nationalCentral);
			// System.out.println(nationalWest);

		}
	}// added for constructor

	public Division getNationalEast() {
		return nationalEast;
	}

	public void setNationalEast(Division nationalEast) {
		this.nationalEast = nationalEast;
	}

	public Division getNationalCentral() {
		return nationalCentral;
	}

	public void setNationalCentral(Division nationalCentral) {
		this.nationalCentral = nationalCentral;
	}

	public Division getNationalWest() {
		return nationalWest;
	}

	public void setNationalWest(Division nationalWest) {
		this.nationalWest = nationalWest;
	}

	public Division getAmericanEast() {
		return americanEast;
	}

	public void setAmericanEast(Division americanEast) {
		this.americanEast = americanEast;
	}

	public Division getAmericanCentral() {
		return americanCentral;
	}

	public void setAmericanCentral(Division americanCentral) {
		this.americanCentral = americanCentral;
	}

	public Division getAmericanWest() {
		return americanWest;
	}

	public void setAmericanWest(Division americanWest) {
		this.americanWest = americanWest;
	}

	public String toString() {
		return String.format("%s", nationalEast, nationalCentral, nationalWest);
	}

	/** used to remove any leading/trailing spaces randomly in the file */
	public static String removeSpaces(String name) {
		String fullName = "";
		String[] team = name.split("\\s+");
		for (String x : team) {
			fullName = fullName + " " + x;
		}
		return fullName;
	}

	/** Method to convert from string to int */
	public static int getMonth(String month) {
		month = month.toLowerCase();
		if (month.equals("jan")) {
			return 1;
		} else if (month.equals("feb")) {
			return 2;
		} else if (month.equals("mar")) {
			return 3;
		} else if (month.equals("apr")) {
			return 4;
		} else if (month.equals("may")) {
			return 5;
		} else if (month.equals("jun")) {
			return 6;
		} else if (month.equals("jul")) {
			return 7;
		} else if (month.equals("aug")) {
			return 8;
		} else if (month.equals("sep")) {
			return 9;
		} else if (month.equals("oct")) {
			return 10;
		} else if (month.equals("nov")) {
			return 11;
		} else {
			return 12;
		}
	}
}
