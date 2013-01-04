package uk.ac.gla.dcs.tp3.w.parser;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import uk.ac.gla.dcs.tp3.w.league.*;

public class AltParser {

	private boolean verbose = false;
	private Date current = new Date();
	private HashMap<String, Division> divisions;

	public AltParser() {
	}

	public boolean parse(String fileName) {
		File f;
		Scanner fs;
		String[] line;
		try {
			f = new File(fileName);
			fs = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		while (fs.hasNextLine()) {
			line = fs.nextLine().split(" ");
			if (verbose) {
				System.out.print("[");
				for (String s : line)
					System.out.print(s + ", ");
				System.out.println("]: Length = " + line.length);
			}
			if (line.length <= 1)
				continue;
			else if (line.length == 3)
				newDate(line);
			else
				newMatch(line);
		}
		fs.close();
		return true;
	}

	private void newDate(String[] line) {
		int day = Integer.parseInt(line[0]);
		int year = Integer.parseInt(line[2]);
		current = new Date(day, line[1], year);
		if (verbose) {
			System.out.println(day + " " + line[1] + " " + year);
			System.out.println("--------------------\n" + "NEW DATE: "
					+ current + "\n--------------------");
		}
	}

	private void newMatch(String[] line) {
		String[] time = line[0].split(":");
		DateTime matchDate = new DateTime(current, Integer.parseInt(time[0]),
				Integer.parseInt(time[1]));
		int firstScore = -1;
		int secondScore = -1;
		String[] score = line[line.length - 1].split(":");
		boolean played = false;
		if (score.length == 2) {
			firstScore = Integer.parseInt(score[0]);
			secondScore = Integer.parseInt(score[1]);
			played = true;
		}
		String firstTeam = "";
		String secondTeam = "";
		int i = 1;
		for (i = 1; !line[i].equals("-"); i++)
			firstTeam += line[i] + " ";
		firstTeam = firstTeam.trim();
		for (i++; i < line.length - 2; i++)
			secondTeam += line[i] + " ";
		secondTeam = secondTeam.trim();
		if (verbose) {
			System.out.println("MATCH:");
			System.out.println("\t" + firstTeam + " plays " + secondTeam);
			System.out.println("\t\ton: " + matchDate);
			System.out.println("\t\tresult: " + firstScore + ":" + secondScore);
			System.out.println("\t\tplayed: " + played);
		}
	}

	public void setVerbose() {
		verbose = true;
	}

	public HashMap<String, Division> getDivisions() {
		return divisions;
	}

	public static void main(String[] args) {
		AltParser ap = new AltParser();
		ap.setVerbose();
		ap.parse(args[0]);
	}
}
