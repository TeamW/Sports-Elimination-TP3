package uk.ac.gla.dcs.tp3.w.ui;

import uk.ac.gla.dcs.tp3.w.algorithm.Algorithm;
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
	private static final double SPACING = 1.25;
	private HashMap<String, Division> divisions;
	private Table table;
	private String[] leagues = { "National", "American" };
	private String[] divisionNames = { "West", "Central", "East" };
	private DateTime displayDate;

	public MainFrame(HashMap<String, Division> d) {
		divisions = d;
		initUI();
	}

	public final void initUI() {
		// Set up the panels with appropriate layout managers
		JPanel screenPanel = new JPanel(new BorderLayout());
		JPanel tablePanel = new JPanel(new BorderLayout());
		JPanel navPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());

		// Add panels to the screen panel and then add that to the JFrame
		screenPanel.add(topPanel, BorderLayout.PAGE_START);
		screenPanel.add(tablePanel, BorderLayout.CENTER);
		screenPanel.add(navPanel, BorderLayout.PAGE_END);
		getContentPane().add(screenPanel);

		// Add the division and league radio buttons
		initTopPanel(topPanel);

		// Add the JTable for showing league data
		initTable(tablePanel);

		// Add the panel that shows the previous/next week buttons
		initNavPanel(navPanel);

		// temp hard-code final date in text file, will auto-calc this in next
		// iteration
		calcStartDate();
		validateDate();
		
		// Set the JFrame's attributes
		setTitle("Team W - Algorithms for Sports Eliminations");
		setLocation(100, 100);
		setVisible(true);
		pack();
		setSize((int) (getWidth() * SPACING), getHeight());
		setMinimumSize(new Dimension(getWidth(), getHeight()));
	}

	// get the last date in the fixtures
	private void calcStartDate() {
		DateTime date = divisions.get(table.getCurrent()).getFixtures().get(0)
				.getDateTime();
		for (Division d : divisions.values()) {
			for (Match m : d.getFixtures()) {
				if (m.getDateTime().before(date)) 
					date = m.getDateTime();
			}
		}
		displayDate = date;
		System.out.println("Starting date is: " + displayDate.toString());
	}

	// loop through every game played in the current division,
	// check if date is less than/equal to current date,
	// if not unplay match
	private void validateDate() {
		for (Division d : divisions.values()) {
			for (Match m : d.getFixtures()) {
				if (m.getDateTime().before(displayDate)) {
					m.playMatch();
				} else {
					m.unplayMatch();
				}
			}
			for (Team t : d.getTeams()) {
				t.setEliminated(false);
				ArrayList<Team> teams = t.getEliminatedBy();
				t.getEliminatedBy().removeAll(teams);
			}
			(new Algorithm(d)).updateDivisionElim();
		}
		table.setCurrent(table.getCurrent());
	}

	private void initTopPanel(JPanel topPanel) {
		JLabel dateLabel = new JLabel("Current date: " + new Date().toString(),
				JLabel.CENTER);
		topPanel.add(dateLabel, BorderLayout.NORTH);
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
		initRadioButtons(radioPanel);
		topPanel.add(radioPanel, BorderLayout.SOUTH);
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
			}
		};

		JPanel leaguePanel = new JPanel();
		leaguePanel.add(new JLabel("League: "));
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
		for (String s : divisionNames) {
			JRadioButton rButton3 = new JRadioButton(s);
			if (s.equals("West"))
				rButton3.setSelected(true);
			rButton3.addActionListener(divisionListener);
			divisionGroup.add(rButton3);
			divisionPanel.add(rButton3);
		}
		radioPanel.add(divisionPanel, BorderLayout.CENTER);

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
		JButton backButton = new JButton("Previous Day");
		backButton.setToolTipText("Move to previous day of results");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// decrement the day (need to make this work with month changes)
				// then update the model
				System.out.println("Back");
				displayDate.decrementDate();
				System.out.println("Current date is now "
						+ displayDate.toString());
				validateDate();
			}
		});
		navPanel.add(backButton, BorderLayout.WEST);
		JButton nextButton = new JButton("Next Day");
		nextButton.setToolTipText("Move to next day of results");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// increment the day (need to make this work with month changes)
				// then update the model
				System.out.println("Next");
				displayDate.incrementDate();
				System.out.println("Current date is now "
						+ displayDate.toString());
				validateDate();
			}
		});
		navPanel.add(nextButton, BorderLayout.EAST);
	}

	private void initTable(JPanel tablePanel) {
		table = new Table(divisions);
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(new TableMouseListener(table, divisions, this));
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