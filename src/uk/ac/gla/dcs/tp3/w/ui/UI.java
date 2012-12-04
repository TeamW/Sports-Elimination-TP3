package uk.ac.gla.dcs.tp3.w.ui;

import uk.ac.gla.dcs.tp3.w.Main;
import uk.ac.gla.dcs.tp3.w.league.Division;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author david
 *
 */
public class UI extends JFrame {

	private static final long serialVersionUID = 1L;

	public UI(Division d) {
        initUI(d);
    }
	
	public final void initUI(Division d) {
		 //full screen panel
		 JPanel screenPanel = new JPanel();
		 getContentPane().add(screenPanel);
		 
		 //the table panel
	     JPanel tablePanel = new JPanel();

	     //the navigation panel
	     JPanel navPanel = new JPanel();
	     
	     //the radio buttons panel
	     JPanel radioPanel = new JPanel();
	     
	    //set borders
	     radioPanel.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
	     screenPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
	     
	     //set layouts and add the panels to the screen panel
	     screenPanel.setLayout(new BorderLayout());
	     radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
	     tablePanel.setLayout(new BorderLayout());
	     navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.X_AXIS));
	     screenPanel.add(radioPanel, BorderLayout.PAGE_START);
	     screenPanel.add(tablePanel, BorderLayout.CENTER);
	     screenPanel.add(navPanel, BorderLayout.PAGE_END);
	     
	     //set up table
	     initTable(tablePanel, d);

	     //NAV panel buttons
	     initNavPanel(navPanel);

	     //Create the radio buttons.
	     initRadioButtons(radioPanel);
	     
	     //set general frame stuff
	     setTitle("Team W UI early iteration");
	     setResizable(false);
	     setSize(800, 600);
	     setLocationRelativeTo(null);
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initTable(JPanel tablePanel, Division d)
	{
		String[] columnNames = {"Team",
                "Points",
                "Games Played",
                "Is Eliminated"};

	     String[][] data = new String[d.getTeams().size()][columnNames.length];
	     
	     for(int i = 0; i < d.getTeams().size(); i++){
	    	 for(int j = 0; j < columnNames.length; j++){
	    		 switch(j){
	    		 	case(0):{
	    		 		data[i][j] = d.getTeams().get(i).getName();
	    		 		break;
	    		 	}
	    		 	case(1):{
	    		 		data[i][j] = String.valueOf(d.getTeams().get(i).getPoints());
	    		 		break;
	    		 	}
	    		 	case(2):{
	    		 		data[i][j] = String.valueOf(d.getTeams().get(i).getGamesPlayed());
	    		 		break;
	    		 	}
	    		 	case(3):{
	    		 		data[i][j] = String.valueOf(d.getTeams().get(i).isEliminated());
	    		 		break;
	    		 	}
	    		 }
	 
	    	 }
	     }

	     final JTable table = new JTable(data, columnNames);
	     table.setFillsViewportHeight(true);
	     
	     //Create the scroll pane and add the table to it.
	     JScrollPane scrollPane = new JScrollPane(table);
	     //Add the scroll pane to this panel.
	     tablePanel.add(scrollPane, BorderLayout.PAGE_START);
	}
	
	private void initNavPanel(JPanel navPanel)
	{
		//NAV panel buttons
	     JButton backButton = new JButton("Previous Week");
	     backButton.setToolTipText("Move to previous week of results");
	     backButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	             //System.exit(0);
	        }
	     });
	     navPanel.add(backButton);
	     navPanel.add(Box.createRigidArea(new Dimension(548,0)));
	     
	     JButton nextButton = new JButton("Next Week");
	     nextButton.setToolTipText("Move to next week of results");
	     nextButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	             //System.exit(0);
	        }
	     });
	     navPanel.add(nextButton);
	}
	
	private void initRadioButtons(JPanel radioPanel)
	{
		//button change listeners
		ActionListener leagueListener = new ActionListener() {
	         public void actionPerformed(ActionEvent event) 
	         {
	        	 //filter by league
	        	 
	        }
		};
		ActionListener divisionListener = new ActionListener() {
	         public void actionPerformed(ActionEvent event) 
	         {
	        	 System.out.println("Changed the division");
	        }
		};
		
		//radio button groups
		ButtonGroup leagueGroup = new ButtonGroup();
		ButtonGroup divisionGroup = new ButtonGroup();
		
		//now set up each button and add it to the group
		JRadioButton rButton1 = new JRadioButton("National");
		rButton1.setSelected(true);
		leagueGroup.add(rButton1);
		radioPanel.add(rButton1);
		rButton1.addActionListener(leagueListener);
		radioPanel.add(Box.createRigidArea(new Dimension(20,0)));

		JRadioButton rButton2 = new JRadioButton("American");
		rButton2.addActionListener(leagueListener);
		leagueGroup.add(rButton2);
		radioPanel.add(rButton2);
		radioPanel.add(Box.createRigidArea(new Dimension(240,0)));

		JRadioButton rButton3 = new JRadioButton("West");
		rButton3.setSelected(true);
		rButton3.addActionListener(divisionListener);
		divisionGroup.add(rButton3);
		radioPanel.add(rButton3);
		radioPanel.add(Box.createRigidArea(new Dimension(20,0)));

		JRadioButton rButton4 = new JRadioButton("Central");
		divisionGroup.add(rButton4);
		rButton4.addActionListener(divisionListener);
		radioPanel.add(rButton4);
		radioPanel.add(Box.createRigidArea(new Dimension(20,0)));

		JRadioButton rButton5 = new JRadioButton("East");
		divisionGroup.add(rButton5);
		rButton5.addActionListener(divisionListener);
		radioPanel.add(rButton5);
		radioPanel.add(Box.createRigidArea(new Dimension(34,0)));

		//set up the quit button
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Click here to exit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		radioPanel.add(Box.createRigidArea(new Dimension(50,0)));
		radioPanel.add(quitButton);
	}

}
