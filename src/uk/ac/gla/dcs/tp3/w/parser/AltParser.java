package uk.ac.gla.dcs.tp3.w.parser;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import uk.ac.gla.dcs.tp3.w.league.*;

public class AltParser {

	private boolean verbose = false;
	private Date current = new Date();
	private HashMap<String, Division> divisions = new HashMap<String, Division>();

	public AltParser() {
	}

	public boolean parse(String fileName) {
		Scanner fs;
		String[] line;
		try {
			fs = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		init();
		while (fs.hasNextLine()) {
			line = fs.nextLine().split(" ");
			if (verbose) {
				System.out.print("[");
				for (String s : line)
					System.out.print(s + ", ");
				System.out.println("]: Length = " + line.length);
			}
			if (line.length > 1)
				if (line.length == 3)
					newDate(line);
				else
					newMatch(line);
		}
		fs.close();
		return true;
	}

	private void init() {
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
		int homeScore = -1;
		int awayScore = -1;
		String[] score = line[line.length - 1].split(":");
		boolean played = false;
		if (score.length == 2) {
			homeScore = Integer.parseInt(score[0]);
			awayScore = Integer.parseInt(score[1]);
			played = true;
		}
		String firstTeam = "";
		String secondTeam = "";
		int i = 1;
		for (i = 1; !line[i].equals("-"); i++)
			firstTeam += line[i] + " ";
		firstTeam = firstTeam.trim();
		for (i++; i < line.length - 1; i++)
			secondTeam += line[i] + " ";
		secondTeam = secondTeam.trim();
		if (verbose) {
			System.out.println("MATCH:");
			System.out.println("\t" + firstTeam + " plays " + secondTeam);
			System.out.println("\t\ton: " + matchDate);
			System.out.println("\t\tresult: " + homeScore + ":" + awayScore);
			System.out.println("\t\tplayed: " + played);
		}
		Team homeTeam = getTeam(firstTeam);
		Team awayTeam = getTeam(secondTeam);
		Division d = divisions.get(getDivisionName(homeTeam));
		if (d == null || homeTeam == null || awayTeam == null)
			return;
		Match m = new Match(homeTeam, awayTeam, homeScore, awayScore,
				matchDate, false);
		homeTeam.addUpcomingMatch(m);
		awayTeam.addUpcomingMatch(m);
		d.addFixture(m);
		if (played)
			m.updatePointsAndPlayGame();
	}

	private String getDivisionName(Team t) {
		for (Division d : divisions.values())
			if (d.getTeams().contains(t))
				return d.getName();
		return null;
	}

	private Team getTeam(String s) {
		for (Division d : divisions.values())
			for (Team t : d.getTeams())
				if (t.getName().equalsIgnoreCase(s))
					return t;
		return null;
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
