package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class MainGameWindow {

	private JFrame frmFarmSimulator;
	
	
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameWindow window = new MainGameWindow(new Game());
					window.frmFarmSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGameWindow(Game game) {
		this.game = game;
		initialize();
		frmFarmSimulator.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSimulator = new JFrame();
		frmFarmSimulator.setResizable(false);
		frmFarmSimulator.setTitle("Farm Simulator");
		frmFarmSimulator.setBounds(100, 100, 1000, 600);
		frmFarmSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSimulator.getContentPane().setLayout(null);
		
		JLabel farmTitlelbl = new JLabel("");
		farmTitlelbl.setBounds(10, 22, 976, 48);
		farmTitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		farmTitlelbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		farmTitlelbl.setText(game.getPlayerFarm().getName());
		frmFarmSimulator.getContentPane().add(farmTitlelbl);
		
		JLabel daysRemaininglbl = new JLabel("New label");
		daysRemaininglbl.setBounds(10, 527, 338, 35);
		daysRemaininglbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		daysRemaininglbl.setText("Days remaining: " + game.getDaysRemaining());
		frmFarmSimulator.getContentPane().add(daysRemaininglbl);
		
		JButton nextDayButton = new JButton("Next Day");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = game.dailyChange();
				if (message != "") {
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);
				}
				
				if (game.getDaysRemaining() > 0) {
					MainGameWindow window = new MainGameWindow(game);
					frmFarmSimulator.dispose();
			
		        }
				
				
				
			}
		});
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nextDayButton.setBounds(855, 533, 131, 29);
		frmFarmSimulator.getContentPane().add(nextDayButton);
	}

}
