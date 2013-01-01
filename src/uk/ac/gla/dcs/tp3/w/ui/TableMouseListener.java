package uk.ac.gla.dcs.tp3.w.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JOptionPane;

import uk.ac.gla.dcs.tp3.w.league.Division;
import uk.ac.gla.dcs.tp3.w.league.Team;

public class TableMouseListener implements MouseListener {
	private Table table;
	private HashMap<String, Division> divisions;
	private MainFrame mainFrame;
	private String s;
	private Division d;
	private int r;

	public TableMouseListener(Table t, HashMap<String, Division> h, MainFrame mf) {
		table = t;
		divisions = h;
		mainFrame = mf;
	}

	public void mouseClicked(MouseEvent e) {
		r = table.getSelectedRow();
		if (validClick()) {
			s = (String) table.getValueAt(r, 0);
			d = divisions.get(table.getCurrent());
			if (d != null)
				for (Team team : d.getTeams())
					if (validTeam(team))
						showElimination(team);
		}
	}

	private boolean validClick() {
		return table.getSelectedColumn() == 3;
	}

	private boolean validTeam(Team t) {
		return t.getName().equalsIgnoreCase(s) && t.isEliminated();
	}

	private void showElimination(Team t) {
		String s = t.getName() + " has been eliminated by ";
		String cOE = "Certificate of elimination for " + t.getName();
		int size = t.getEliminatedBy().size();
		int i = 0;
		for (Team team : t.getEliminatedBy())
			if (++i < size)
				s += team.getName() + ", ";
			else if (size != 1)
				s += "and " + team.getName() + ".";
			else
				s += team.getName() + ".";
		JOptionPane.showMessageDialog(mainFrame, s, cOE,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
