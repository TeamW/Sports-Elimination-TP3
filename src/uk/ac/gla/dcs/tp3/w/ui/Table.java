package uk.ac.gla.dcs.tp3.w.ui;

import java.util.HashMap;

import javax.swing.JTable;

import uk.ac.gla.dcs.tp3.w.league.Division;

public class Table extends JTable {

	private static final long serialVersionUID = 1L;
	private TableModel model;

	public Table(HashMap<String, Division> divisions) {
		super();
		model = new TableModel(divisions);
		this.setModel(model);
	}

	public String getCurrent() {
		return model.getCurrent();
	}

	public void setCurrent(String s) {
		model.setCurrent(s);
	}

}
