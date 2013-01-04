package uk.ac.gla.dcs.tp3.w.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import uk.ac.gla.dcs.tp3.w.league.Date;
import uk.ac.gla.dcs.tp3.w.league.DateTime;

public class AltParser {

	private boolean verbose = false;

	public AltParser() {
	}

	public boolean parse(String fileName) {
		File f;
		Scanner fs;
		String line;
		String[] splitLine;
		try {
			f = new File(fileName);
			fs = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		Date current = new Date();

		while (fs.hasNextLine()) {
			line = fs.nextLine();
			splitLine = line.split(" ");
			if (verbose) {
				System.out.print("[");
				for (String s : splitLine)
					System.out.print(s + ", ");
				System.out.println("]: Length = " + splitLine.length);
			}
			if (splitLine.length == 1) {
				continue;
			} else if (splitLine.length == 3) {
				int day = Integer.parseInt(splitLine[0]);
				int year = Integer.parseInt(splitLine[2]);
				System.out.println(day + " " + splitLine[1] + " " + year);
				current = new Date(day, splitLine[1], year);

				if (verbose) {
					System.out.println("--------------------\n" + "NEW DATE: "
							+ current.toString() + "\n--------------------");
				}
			} else {
				String time = splitLine[0];
				String[] timeSplit = time.split(":");
				DateTime matchDate = new DateTime(current, Integer
						.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1]));

				int firstScore = -1;
				int secondScore = -1;
				String score = splitLine[splitLine.length - 1];
				String[] scoreSplit = score.split(":");
				if (scoreSplit.length > 1) {
					firstScore = Integer.parseInt(scoreSplit[0]);
					secondScore = Integer.parseInt(scoreSplit[1]);
				}

				StringBuilder firstTeamsb = new StringBuilder();
				StringBuilder secondTeamsb = new StringBuilder();
				for (int i = 1; i < splitLine.length - 2; i++) {
					if (splitLine[i] == "-") {
						for (int j = i + 1; j < splitLine.length - 2; j++) {
							if (secondTeamsb.length() != 0)
								secondTeamsb.append(" ");
							secondTeamsb.append(splitLine[j]);
						}
						break;
					}
					if (firstTeamsb.length() != 0)
						firstTeamsb.append(" ");
					firstTeamsb.append(splitLine[i]);
				}

				String firstTeam = firstTeamsb.toString();
				String secondTeam = secondTeamsb.toString();
				/*
				 * Everything is now in variables to be added to match/league
				 * classes I think. Tested using baseballSource.txt.
				 */

				if (verbose) {
					System.out.println("MATCH:");
					System.out.println("\t" + firstTeam + " plays "
							+ secondTeam);
					System.out.println("\t\ton: " + matchDate.toString());
					System.out.println("\t\tresult: " + firstScore + ":"
							+ secondScore);
				}
			}
		}

		return true;
	}

	public void setVerbose() {
		verbose = true;
	}

	public static void main(String[] args) {
		AltParser ap = new AltParser();
		ap.setVerbose();
		ap.parse(args[0]);
	}
}
