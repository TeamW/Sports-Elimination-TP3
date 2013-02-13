package uk.ac.gla.dcs.tp3.w.print;

import java.io.*;
import java.util.LinkedList;

import uk.ac.gla.dcs.tp3.w.league.DateTime;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class LaTeXFile {
	StringBuilder sb;
	String fileName;
	String directory;
	String endDir;
	LinkedList<DocumentSection> sections;

	public LaTeXFile() {
		sb = new StringBuilder();
		fileName = null;
		sections = new LinkedList<DocumentSection>();
		directory = null;
	}

	private void documentStart() {
		sb.append("\\documentclass{report}\n\\usepackage{verbatim}\n\\begin{document}\n");
	}

	private void documentEnd() {
		sb.append("\\end{document}\n");
	}

	private void addTable(String title, String colformat, String contents) {
		this.sections.add(new Table(title, colformat, contents));
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
		addTable(d.getName(), colformat, divsb.toString());
	}

	public void addDivisionFromJTable(uk.ac.gla.dcs.tp3.w.ui.Table table,
			DateTime date) {
		int numTeams = table.getRowCount();
		String colformat = "| l | l | l | l |";
		StringBuilder divsb = new StringBuilder();
		divsb.append("Team & Points & Games Played & Elimination Status \\\\ \\hline");
		int j;
		for (int i = 0; i < numTeams; i++) {
			divsb.append("\n");
			for (j = 0; j < 3; j++) {
				divsb.append(table.getValueAt(i, j));
				divsb.append(" & ");
			}
			divsb.append(table.getValueAt(i, j));
			divsb.append("\\\\");
		}
		divsb.append("\\hline\n");
		addTable(table.getCurrent() + " " + date.genDate(), colformat,
				divsb.toString());
	}

	public void addTextSection(String title, String s) {
		TextSection ts = new TextSection(title, s);
		this.sections.add(ts);
	}

	public void addTextSection(String title, StringBuilder s) {
		TextSection ts = new TextSection(title, "");
		ts.append(s);
		this.sections.add(ts);
	}

	public int getNumContents() {
		return sections.size();
	}

	public String getTitles() {
		if (sections.size() == 0)
			return null;
		StringBuilder s = new StringBuilder();
		for (DocumentSection d : sections) {
			s.append(d.getTitle() + "\n");
		}
		return s.toString();
	}

	public void clearDocument() {
		sections = new LinkedList<DocumentSection>();
	}

	public boolean write(String fileName, String endDir) {
		this.fileName = fileName;
		this.directory = System.getProperty("user.dir")
				+ "/src/uk/ac/gla/dcs/tp3/w/";
		this.endDir = endDir;
		File f = new File(directory + fileName + ".tex");
		System.out.println(directory + fileName + ".tex");
		try {
			if (f.createNewFile())
				System.out.println("Created File");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (fileName == null)
			return false;
		documentStart();
		for (DocumentSection ds : sections) {
			sb.append(ds.write());
		}
		documentEnd();

		try {
			FileWriter fstream = new FileWriter(directory + fileName + ".tex");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(sb.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		Runtime r = Runtime.getRuntime();
		String os = System.getProperty("os.name");
		try {
			Process compile;
			if (os.toLowerCase().contains("mac")) {
				compile = r.exec("/usr/texbin/pdflatex " + directory + fileName
						+ ".tex");
			} else {
				compile = r.exec("pdflatex " + directory + fileName + ".tex");
			}
			compile.waitFor();
			Process remove = r.exec("rm " + fileName + ".log " + fileName
					+ ".aux " + directory + fileName + ".tex");
			remove.waitFor();
			Process move = r.exec("mv " + fileName + ".pdf "
					+ endDir.replace(" ", ""));
			move.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clearDocument();
		return true;
	}
}
