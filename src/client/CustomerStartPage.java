
package client;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class CustomerStartPage extends JPanel {

	private static final long serialVersionUID = -8164412480994553957L;
	
	public JButton helpBtn;
	public JButton refillBtn;
	public JPanel utilityPanel;

	public CustomerStartPage(ClientFrame frame) {
		super();
		setLayout(null);
		setBounds(0, 0, 1039, 656);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1039, 522);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		utilityPanel = new JPanel();
		utilityPanel.setBounds(0, 523, 1039, 133);
		add(utilityPanel);
		utilityPanel.setLayout(null);

		helpBtn = new JButton();
		helpBtn.setText("Help");
		helpBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		helpBtn.setBounds(480, 5, 120, 74);
		utilityPanel.add(helpBtn);
		helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getHelp();
			}
		});
		
		/*
		ImageIcon icon = new ImageIcon("./src/client/help.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance(140, 140,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		helpBtn.setIcon(icon);
		*/

		refillBtn = new JButton();
		refillBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		refillBtn.setText("Refill");
		refillBtn.setBounds(625, 5, 120, 74);
		utilityPanel.add(refillBtn);

		JButton loginOutBtn = new JButton("Rewards");
		loginOutBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 25));
		loginOutBtn.setBounds(27, 34, 120, 47);
		utilityPanel.add(loginOutBtn);

		JLabel lblNewLabel = new JLabel("Rewards Member? Login to continue for tasty treats and rewards!");
		lblNewLabel.setBounds(27, 0, 398, 37);
		utilityPanel.add(lblNewLabel);
		
		JButton musicBtn = new JButton();
		musicBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		musicBtn.setText("Music");
		musicBtn.setBounds(809, 5, 120, 74);
		utilityPanel.add(musicBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to Seven Guys! Click one of the options below to get started.");
		lblNewLabel_1.setFont(new Font("Haettenschweiler", Font.BOLD, 30));
		lblNewLabel_1.setBounds(115, 16, 835, 32);
		mainPanel.add(lblNewLabel_1);
		
		JButton orderBtn = new JButton("ORDER");
		orderBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 89));
		orderBtn.setBounds(0, 64, 346, 444);
		mainPanel.add(orderBtn);
		
		JButton payBtn = new JButton("PAY");
		payBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 89));
		payBtn.setBounds(347, 64, 346, 444);
		mainPanel.add(payBtn);
		
		JButton gamesBtn = new JButton("PLAY");
		gamesBtn.setFont(new Font("Haettenschweiler", Font.PLAIN, 89));
		gamesBtn.setBounds(694, 64, 346, 444);
		mainPanel.add(gamesBtn);
	
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();
		Color color1 = Color.WHITE;
		Color color2 = Color.lightGray;
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}
}