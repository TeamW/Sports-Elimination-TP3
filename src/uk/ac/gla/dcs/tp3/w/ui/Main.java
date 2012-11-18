package uk.ac.gla.dcs.tp3.w.ui;

import javax.swing.SwingUtilities;


/**
 * @author gordon
 *
 */
public class Main {

	//TEST EDIT
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UI ui = new UI();
                ui.setVisible(true);
            }
        });
	}

}
