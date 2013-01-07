package uk.ac.gla.dcs.tp3.w.parser;

public class Main {

	public static void main(String[] args) {
		Parser ap = new Parser();
		ap.setVerbose();
		ap.parse(args[0]);
	}

}
