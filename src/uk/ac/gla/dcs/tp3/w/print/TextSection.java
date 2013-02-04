package uk.ac.gla.dcs.tp3.w.print;

public class TextSection extends DocumentSection{

	public TextSection(String text) {
		super();
		sb.append(text);
	}
	
	public void append(String text){
		sb.append(text);
	}
	
	public void append(StringBuilder text){
		sb.append(text);
	}
	
	@Override
	public String write(){
		sb.append("\\\\ \\\\");
		return sb.toString();
	}
}
