package uk.ac.gla.dcs.tp3.w.print;

import java.io.IOException;
import java.util.ArrayList;

import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Working Directory = "
				+ System.getProperty("user.dir"));
		String directory = System.getProperty("user.dir")
				+ "/src/uk/ac/gla/dcs/tp3/w/print/";
		LaTeXFile LF = new LaTeXFile(directory + "output.tex");

		Team atlanta = new Team();
		atlanta.setName("Atlanta");
		atlanta.setGamesPlayed(170 - 8);
		atlanta.setPoints(83);
		atlanta.setEliminated(false);

		Team philadelphia = new Team();
		philadelphia.setName("Philadelphia");
		philadelphia.setGamesPlayed(170 - 4);
		philadelphia.setPoints(79);
		philadelphia.setEliminated(true);

		Team newYork = new Team();
		newYork.setName("New York");
		newYork.setGamesPlayed(170 - 7);
		newYork.setPoints(78);
		newYork.setEliminated(false);

		Team montreal = new Team();
		montreal.setName("Montreal");
		montreal.setGamesPlayed(170 - 5);
		montreal.setPoints(76);
		montreal.setEliminated(true);

		ArrayList<Team> t = new ArrayList<Team>();
		t.add(atlanta);
		t.add(philadelphia);
		t.add(newYork);
		t.add(montreal);

		Division d = new Division(t, null);
		LF.addDivisionInfo(d);
		LF.addTextSection("This is a section of text");
		LF.addDivisionInfo(d);
		if (LF.write()) {
			System.out.println("Success");
		} else {
			System.out.println("Error");
		}

		try {
			Runtime r = Runtime.getRuntime();
			Process compile = r.exec("pdflatex " + directory + "output.tex");
			compile.waitFor();
			Process remove = r
					.exec("rm output.log output.aux src/uk/ac/gla/dcs/tp3/w/print/output.tex");
			remove.waitFor();
			Process move = r
					.exec("mv output.pdf src/uk/ac/gla/dcs/tp3/w/print/");
			move.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
