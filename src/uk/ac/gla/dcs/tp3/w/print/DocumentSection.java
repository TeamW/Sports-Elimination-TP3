package uk.ac.gla.dcs.tp3.w.print;

public abstract class DocumentSection {

	protected StringBuilder sb;
	protected String title;

	public DocumentSection(String title) {
		sb = new StringBuilder();
		this.title = title;
	}

	public StringBuilder getStringBuilder() {
		return sb;
	}

	public String getTitle() {
		return title;
	}

	public String write() {
		return sb.toString();
	}

}
