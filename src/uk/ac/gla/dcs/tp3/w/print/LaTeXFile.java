package uk.ac.gla.dcs.tp3.w.print;

import java.util.LinkedList;

public class LaTeXFile {
	StringBuilder sb;
	String fileName;
	LinkedList<DocumentSection> sections; 
	
	public LaTeXFile(){
		sb = null;
		fileName = null;
		sections = null;
	}
	
	public LaTeXFile(String fileName){
		sb = new StringBuilder();
		this.fileName = fileName;
		sections = new LinkedList<DocumentSection>();
	}
	
	private void documentStart(){
		sb.append("\\documentclass{report}\n\\usepackage{verbatim}\n");
	}
	
	private void documentEnd(){
		sb.append("\\end{document}\n");
	}
	
	private void addTable(String columns, String contents){
		this.sections.add(new Table(columns,contents));
	}
}
