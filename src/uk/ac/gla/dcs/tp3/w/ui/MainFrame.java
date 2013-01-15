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
	private static final double SPACING = 1.25;
	private HashMap<String, Division> divisions;
	private Table table;
	private String[] leagues = { "National", "American" };
	private String[] divisionNames = { "West", "Central", "East" };

	public MainFrame(HashMap<String, Division> d) {
		divisions = d;
		initUI();
	}

	public final void initUI() {
		// Set up the panels with appropriate layout managers
		JPanel screenPanel = new JPanel(new BorderLayout());
		JPanel tablePanel = new JPanel(new BorderLayout());
		JPanel navPanel = new JPanel(new BorderLayout());
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));

		// Add panels to the screen panel and then add that to the JFrame
		screenPanel.add(radioPanel, BorderLayout.PAGE_START);
		screenPanel.add(tablePanel, BorderLayout.CENTER);
		screenPanel.add(navPanel, BorderLayout.PAGE_END);
		getContentPane().add(screenPanel);

		// Add the division and league radio buttons
		initRadioButtons(radioPanel);

		// Add the JTable for showing league data
		initTable(tablePanel);

		// Add the panel that shows the previous/next week buttons
		initNavPanel(navPanel);

		// Set the JFrame's attributes
		setTitle("Team W - Algorithms for Sports Eliminations");
		setLocation(100, 100);
		setVisible(true);
		pack();
		setSize((int) (getWidth() * SPACING), getHeight());
		setMinimumSize(new Dimension(getWidth(), getHeight()));
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