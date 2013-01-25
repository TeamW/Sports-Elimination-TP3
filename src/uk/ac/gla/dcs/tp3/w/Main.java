package uk.ac.gla.dcs.tp3.w;

import java.util.HashMap;

import javax.swing.SwingUtilities;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.parser.Parser;
import uk.ac.gla.dcs.tp3.w.ui.MainFrame;

/**
 * This is the main method for the entire project. The system can be either run
 * as a desktop application with a Swing interface or, if the command line
 * argument '--web' is specified, it can be run as a text output only program
 * for displaying on a website or some other application.
 * 
 * @author Team W
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		// Set up variables for whole application.
		Parser p = new Parser();
		// Text only output request check
		boolean web = false;
		// Check supplied files were successfully parsed.
		boolean parsed = false;
		for (String s : args) {
			if (s.equals("--web")) {
				web = true;
			} else {
				parsed = p.parse(s);
			}
		}
		// If none of the supplied files were successfully parsed, default to
		// known file.
		if (!parsed) {
			p.parse("");
		}
		// Now there will be some valid divisions to obtain. Now work out
		// elimination status of each team.
		final HashMap<String, Division> map = p.getDivisions();
		for (Division d : map.values()) {
			(new Algorithm(d)).updateDivisionElim();
		}
		// Swing interface requested.
		if (!web) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new MainFrame(map);
				}
			});
		} else {
			// Text only output requested.
			for (Division d : map.values()) {
				d.printWeb();
			}
		}
	}
}
