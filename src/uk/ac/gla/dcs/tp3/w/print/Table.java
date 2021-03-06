package uk.ac.gla.dcs.tp3.w.print;

public class Table extends DocumentSection {

	public Table(String title, String colformat, String contents) {
		super(title);
		sb.append(title + "\n\\\\");
		sb.append(beginTabular(colformat));
		sb.append(contents);
		sb.append(endTabular());
	}

	private String beginTabular(String colformat) {
		return "\\begin{tabular}{" + colformat + "}\n\\hline\n";
	}

	private String endTabular() {
		return "\\end{tabular}\\\\ \\\\";
	}

}
