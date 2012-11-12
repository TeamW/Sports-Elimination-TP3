
import java.awt.event.*;
import javax.swing.*;

/**
 * @author david
 *
 */
public class Example extends JFrame {

	public Example() {
        initUI();
    }
	
	public final void initUI() {
	     JPanel panel = new JPanel();
	     getContentPane().add(panel);

	     panel.setLayout(null);

	     JButton quitButton = new JButton("Quit");
	     quitButton.setBounds(700, 530, 80, 30);
	     quitButton.setToolTipText("Click here to exit");
	     quitButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	             System.exit(0);
	        }
	     });
	     panel.add(quitButton);
	     
	     JTextField text = new JTextField("Type here");
	     text.setBounds(300, 250, 100, 30);
	     panel.add(text);
	     
	     
	     JLabel label = new JLabel("Simple Test Label");
	     label.setBounds(300, 200, 120, 50);
	     panel.add(label);

	     setTitle("Quit button");
	     setSize(800, 600);
	     setLocationRelativeTo(null);
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//TEST EDIT
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Example ex = new Example();
                ex.setVisible(true);
            }
        });
	}

}
