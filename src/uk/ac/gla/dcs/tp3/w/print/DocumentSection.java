package uk.ac.gla.dcs.tp3.w.print;

public abstract class DocumentSection {

	public StringBuilder sb;

	public DocumentSection() {
		sb = new StringBuilder();
	}

	public StringBuilder getStringBuilder() {
		return sb;
	}

	public String write() {
		return sb.toString();
	}

}
