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
				+ "/src/uk/ac/gla/dcs/tp3/w/print/output.tex";
		LaTeXFile LF = new LaTeXFile(directory);

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
		if (LF.write()) {
			System.out.println("Success");
		} else {
			System.out.println("FUCK");
		}
		
		try {
			String[] compile = {"pdflatex",directory};
			//String[] move = {"mv", "output.*", "src/uk/gla/dcs/tp3/w/print/"};
			Runtime.getRuntime().exec(compile).waitFor();
			//Runtime.getRuntime().exec(move).waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
