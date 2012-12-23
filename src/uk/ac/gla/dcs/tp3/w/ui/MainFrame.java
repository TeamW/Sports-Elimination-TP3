package uk.ac.gla.dcs.tp3.w.ui;

import uk.ac.gla.dcs.tp3.w.league.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.RowSorter.SortKey;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final double SPACING_CONSTANT = 1.25;
	private HashMap<String, Division> divisions;
	private Table table;

	public MainFrame(HashMap<String, Division> d) {
		divisions = d;
		initUI();
	}

	public final void initUI() {
		// full screen panel
		JPanel screenPanel = new JPanel();
		getContentPane().add(screenPanel);

		// the table panel
		JPanel tablePanel = new JPanel();

		// the navigation panel
		JPanel navPanel = new JPanel();

		// the radio buttons panel
		JPanel radioPanel = new JPanel();

		// set layouts and add the panels to the screen panel
		screenPanel.setLayout(new BorderLayout());
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
		tablePanel.setLayout(new BorderLayout());
		navPanel.setLayout(new BorderLayout());
		screenPanel.add(radioPanel, BorderLayout.PAGE_START);
		screenPanel.add(tablePanel, BorderLayout.CENTER);
		screenPanel.add(navPanel, BorderLayout.PAGE_END);

		// set up table
		initTable(tablePanel);

		// NAV panel buttons
		initNavPanel(navPanel);

		// Create the radio buttons.
		initRadioButtons(radioPanel);

		// set general frame stuff
		setTitle("Team W - Algorithms for Sports Eliminations");
		setLocation(100, 100);
		setVisible(true);
		pack();
		this.setMinimumSize(new Dimension(
				(int) (this.getWidth() * SPACING_CONSTANT), this.getHeight()));
	}

	private void initRadioButtons(JPanel radioPanel) {
		ButtonGroup leagueGroup = new ButtonGroup();
		ButtonGroup divisionGroup = new ButtonGroup();

		ActionListener leagueListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JRadioButton rb = null;
				if (event.getSource() instanceof JRadioButton)
					rb = (JRadioButton) event.getSource();
				else
					return;
				String s = table.getCurrent();
				String[] sa = s.split(" ");
				sa[0] = rb.getText();
				table.setCurrent(sa[0] + " " + sa[1]);
				System.out.println(sa[0] + " " + sa[1]);
			}
		};
		ActionListener divisionListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JRadioButton rb = null;
				if (event.getSource() instanceof JRadioButton)
					rb = (JRadioButton) event.getSource();
				else
					return;
				String s = table.getCurrent();
				String[] sa = s.split(" ");
				sa[1] = rb.getText();
				table.setCurrent(sa[0] + " " + sa[1]);
				System.out.println(sa[0] + " " + sa[1]);
			}

		};

		JPanel leaguePanel = new JPanel();
		leaguePanel.add(new JLabel("League: "));
		String[] leagues = { "National", "American" };
		for (String s : leagues) {
			JRadioButton rButton1 = new JRadioButton(s);
			if (s.equals("National"))
				rButton1.setSelected(true);
			leagueGroup.add(rButton1);
			leaguePanel.add(rButton1);
			rButton1.addActionListener(leagueListener);
		}
		radioPanel.add(leaguePanel, BorderLayout.WEST);

		JPanel divisionPanel = new JPanel();
		divisionPanel.add(new JLabel("Division: "));
		String[] divisions = { "West", "Central", "East" };
		for (String s : divisions) {
			JRadioButton rButton3 = new JRadioButton(s);
			if (s.equals("West"))
				rButton3.setSelected(true);
			rButton3.addActionListener(divisionListener);
			divisionGroup.add(rButton3);
			divisionPanel.add(rButton3);
		}
		radioPanel.add(divisionPanel, BorderLayout.CENTER);

		// set up the quit button
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Click here to exit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		radioPanel.add(quitButton, BorderLayout.EAST);
	}

	private void initNavPanel(JPanel navPanel) {
		JButton backButton = new JButton("Previous Week");
		backButton.setToolTipText("Move to previous week of results");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Back");
			}
		});
		navPanel.add(backButton, BorderLayout.WEST);
		JButton nextButton = new JButton("Next Week");
		nextButton.setToolTipText("Move to next week of results");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Next");
			}
		});
		navPanel.add(nextButton, BorderLayout.EAST);
	}

	private void initTable(JPanel tablePanel) {
		table = new Table(divisions);
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(new MouseListener() {
			String s;
			Division d;
			int c, r;

			public void mouseClicked(MouseEvent e) {
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
				c = table.getSelectedColumn();
				r = table.getSelectedRow();
				return c == 3 && table.getValueAt(r, 0) instanceof String;
			}

			private boolean validTeam(Team team) {
				return team.getName().equalsIgnoreCase(s)
						&& team.isEliminated();
			}

			private void showElimination(Team t) {
				String s = t.getName() + " has been eliminated by ";
				int size = t.getEliminatedBy().size();
				int i = 0;
				for (Team team : t.getEliminatedBy()) {
					i++;
					if (i < size)
						s += team.getName() + ", ";
					else
						s += "and " + team.getName() + ".";
				}
				JOptionPane.showMessageDialog(getParent(), s);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
		DefaultRowSorter<?, ?> sorter = ((DefaultRowSorter<?, ?>) table
				.getRowSorter());
		ArrayList<SortKey> list = new ArrayList<SortKey>();
		list.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sorter.setSortKeys(list);
		sorter.sort();
		JScrollPane sP = new JScrollPane(table);
		tablePanel.add(sP);
	}
}