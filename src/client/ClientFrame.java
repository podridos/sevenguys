package client;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.jvnet.substance.SubstanceLookAndFeel;

import client.utils.Constants;

public class ClientFrame extends JFrame {

	private static final long serialVersionUID = -7181992228161884653L;
	
	private JLabel welcomeBackground;

	public ClientFrame() {
		setTitle(Constants.NAME);
		setSubstanceSkin("Business");
		/*
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int op = JOptionPane.showOptionDialog(new JFrame(), 
						"Are you sure you want to exit the "+Constants.NAME+" client?",
						"Client", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
						new String[] { "Yes", "No" }, "No");
				boolean yes = op == JOptionPane.YES_OPTION;
				if(yes) {
					System.exit(1);
				}
			}

		});
		*/
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1039, 656);
		setLocationRelativeTo(null);

		// Main welcome screen panel background image
		welcomeBackground = new JLabel();
		welcomeBackground.setLayout(null);
		welcomeBackground.setBounds(0, 0, 1039, 656);
		welcomeBackground.setIcon(new ImageIcon(
				new ImageIcon("./src/client/background.png").getImage().
				getScaledInstance(welcomeBackground.getWidth(), welcomeBackground.getHeight(), Image.SCALE_DEFAULT)));
		this.setContentPane(welcomeBackground);
		welcomeBackground.addMouseListener(new MouseListener() {
			// Minimized for simplicity sake
			@Override public void mouseClicked(MouseEvent arg0) { }
			@Override public void mouseEntered(MouseEvent arg0) { }
			@Override public void mouseExited(MouseEvent arg0) { }
			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent e) {
				// This indicates that the user has tapped to continue to the next screen.
				System.out.println("Tapped to continue");
				if(Constants.DEV_MODE) {
					int op = JOptionPane.showOptionDialog(new JFrame(), 
							"Continue as Employee or Customer?",
							"Client", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
							new String[] { "Employee", "Customer" }, "Customer");
					boolean pickedEmployee = op == 0;
					if(pickedEmployee) {
						employeeLandingPage();
					}
					else {
						customerLandingPage();
					}
				}
				else {
					if(Constants.EMPLOYEE_MODE) {
						employeeLandingPage();
					}
					else {
						customerLandingPage();
					}
				}
			}

		});
	}

	private void employeeLandingPage() {
		Client.restart();
	}

	private void customerLandingPage() {
		welcomeBackground.setVisible(false);
		CustomerStartPage panel = new CustomerStartPage(this);
		setContentPane(panel);
		// This lets the server portion know that a customer has connected to the kiosk and assigns
		// them a table/kiosk ID.
		Client.session.getPacketEncoder().sendCustomerConnected();
	}
	
	/**
	 * Notifies any wait staff that help is needed.
	 */
	public void getHelp() {
		Client.session.getPacketEncoder().sendHelpRequest();
	}
	
	/**
	 * Sets the skin for the frame.
	 * @param theme
	 */
	public void setSubstanceSkin(String theme) {
		theme = theme.replace(" ", "");
		try {
			SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin."+theme+"Skin");
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		} catch (Exception e) {
			System.err.println("Substance error: " + e.getMessage());
		}
	}

}
