package uk.ac.gla.dcs.tp3.w.print;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class LaTeXFile {
	StringBuilder sb;
	String fileName;
	LinkedList<DocumentSection> sections;

	public LaTeXFile() {
		sb = null;
		fileName = null;
		sections = null;
	}

	public LaTeXFile(String fileName) {
		sb = new StringBuilder();
		this.fileName = fileName;
		sections = new LinkedList<DocumentSection>();
	}

	private void documentStart() {
		sb.append("\\documentclass{report}\n\\usepackage{verbatim}\n\\begin{document}\n");
	}

	private void documentEnd() {
		sb.append("\\end{document}\n");
	}

	private void addTable(String colformat, String contents) {
		this.sections.add(new Table(colformat, contents));
	}

	public void addDivisionInfo(Division d) {
		Team[] t = d.teamsToArray();
		StringBuilder divsb = new StringBuilder();
		String colformat = "| l | l | l | l |";
		divsb.append("Team & Points & Games Played & Elimination Status \\\\ \\hline");
		for (Team team : t) {
			divsb.append("\n" + team.getName() + " & " + team.getPoints()
					+ " & " + team.getGamesPlayed() + " & "
					+ team.isEliminated() + "\\\\");
		}
		divsb.append("\\hline\n");
		addTable(colformat, divsb.toString());
	}
	
	public void addTextSection(String s){
		TextSection ts = new TextSection(s);
		this.sections.add(ts);
	}
	
	public void addTextSection(StringBuilder s){
		TextSection ts = new TextSection("");
		ts.append(s);
		this.sections.add(ts);
	}

	public boolean write() {
		if (fileName == null)
			return false;
		documentStart();
		for (DocumentSection ds : sections) {
			sb.append(ds.write());
		}
		documentEnd();

		try {
			FileWriter fstream = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(sb.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
