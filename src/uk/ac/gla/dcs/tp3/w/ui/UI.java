package uk.ac.gla.dcs.tp3.w.ui;

import uk.ac.gla.dcs.tp3.w.league.Division;

import java.util.HashMap;

import javax.swing.*;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private HashMap<String, Division> division;
	
	public UI(HashMap<String, Division> d) {
		HashMap<String, Division> division = d;
        initUI();
    }
	
	public final void initUI() {
		
	}

}
