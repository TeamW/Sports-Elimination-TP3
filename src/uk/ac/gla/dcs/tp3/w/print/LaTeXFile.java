package uk.ac.gla.dcs.tp3.w.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class LaTeXFile {
	StringBuilder sb;
	String fileName;
	String directory;
	LinkedList<DocumentSection> sections;

	public LaTeXFile() {
		sb = null;
		fileName = null;
		sections = null;
	}

	public LaTeXFile(String directory, String fileName) {
		sb = new StringBuilder();
		this.fileName = fileName;
		this.directory = directory;
		File f = new File(directory+fileName+".tex");
		System.out.println(directory+fileName+".tex");
		try{
			if(f.createNewFile()) System.out.println("Created File");
		} catch(IOException e){
			e.printStackTrace();
		}
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
	
	public void addDivisionFromJTable(uk.ac.gla.dcs.tp3.w.ui.Table table){
		int numTeams = table.getRowCount();
		String colformat = "| l | l | l | l |";
		StringBuilder divsb = new StringBuilder();
		divsb.append("Team & Points & Games Played & Elimination Status \\\\ \\hline");
		int j;
		for (int i=0;i<numTeams;i++){
			divsb.append("\n");
			for(j=0;j<3;j++){
				System.out.println(i+":"+j);
				divsb.append(table.getValueAt(i,j));//table.convertRowIndexToModel(i), table.convertColumnIndexToModel(j)));
				divsb.append(" & ");
			}
			System.out.println(i+":"+j);
			divsb.append(table.getValueAt(i,j));//table.convertRowIndexToModel(i),table.convertColumnIndexToModel(j)));
			divsb.append("\\\\");
		}
		divsb.append("\\hline\n");
		addTable(colformat,divsb.toString());
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
			FileWriter fstream = new FileWriter(directory+fileName+".tex");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(sb.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			Runtime r = Runtime.getRuntime();
			Process compile = r.exec("pdflatex " +directory +fileName+".tex");
			compile.waitFor();
			Process remove = r
					.exec("rm "+fileName+".log "+fileName+".aux "+fileName+".tex");
			remove.waitFor();
			Process move = r
					.exec("mv "+fileName+".pdf src/uk/ac/gla/dcs/tp3/w/");
			move.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
