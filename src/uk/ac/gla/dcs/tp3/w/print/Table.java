package uk.ac.gla.dcs.tp3.w.print;

public class Table extends DocumentSection{
	
	public Table(String column, String contents){
		sb.append(beginTabular(column));
		sb.append(contents);
		sb.append(endTabular());
	}
	
	private String beginTabular(String column){
		return "\\begin{tabular}{"+column+"}\n";
	}
	
	private String endTabular(){
		return "\\end{tabular}\n";
	}
	
	
}
