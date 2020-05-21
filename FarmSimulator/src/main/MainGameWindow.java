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
import javax.swing.JTextArea;
import java.awt.Color;

public class MainGameWindow {

	private JFrame mainGameWindow;
	
	
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameWindow window = new MainGameWindow(new Game());
					window.mainGameWindow.setVisible(true);
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
		mainGameWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainGameWindow = new JFrame();
		mainGameWindow.setResizable(false);
		mainGameWindow.setTitle("Farm Simulator");
		mainGameWindow.setBounds(100, 100, 1000, 600);
		mainGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGameWindow.getContentPane().setLayout(null);
		
		JLabel farmTitlelbl = new JLabel("");
		farmTitlelbl.setBounds(10, 37, 976, 48);
		farmTitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		farmTitlelbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		farmTitlelbl.setText(game.getPlayerFarm().getName() + " Manager");
		mainGameWindow.getContentPane().add(farmTitlelbl);
		
		JLabel daysRemaininglbl = new JLabel("New label");
		daysRemaininglbl.setBounds(75, 519, 338, 29);
		daysRemaininglbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		daysRemaininglbl.setText("Days remaining: " + game.getDaysRemaining());
		mainGameWindow.getContentPane().add(daysRemaininglbl);
		
		JButton nextDayButton = new JButton("Next Day");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() > 0) {
					String prompt = "Are you sure? You still have " + game.getActions() + " daily actions remaining.";
					JOptionPane nextDayChoose = new JOptionPane();
					int choice = JOptionPane.showConfirmDialog(nextDayChoose, prompt, "Next Day", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						String animalMessage = game.dailyChange();
						if (animalMessage != "") {
							JOptionPane animalFrame = new JOptionPane();
							JOptionPane.showMessageDialog(animalFrame, animalMessage);
						}
						if (game.getDaysRemaining() > 0) {
							String greeter = "Good Morning! You have " + game.getDaysRemaining() + " days remaining.";
							JOptionPane greeterFrame = new JOptionPane();
							JOptionPane.showMessageDialog(greeterFrame, greeter);
							game.mainGameLaunch();
							mainGameWindow.dispose();
				        } else {
				        	// end game
				        }
					}
				}
			}
		});
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nextDayButton.setBounds(836, 519, 131, 29);
		mainGameWindow.getContentPane().add(nextDayButton);
		
		JLabel accBalanceLbl = new JLabel("New label");
		accBalanceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		accBalanceLbl.setBounds(508, 519, 392, 29);
		accBalanceLbl.setText("Account Balance: $" + game.getPlayerFarm().getMoney());
		mainGameWindow.getContentPane().add(accBalanceLbl);
		
		JLabel daysRemainingLbl = new JLabel("Days remaining: 0");
		daysRemainingLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		daysRemainingLbl.setBounds(258, 519, 338, 29);
		daysRemainingLbl.setText("Daily Actions Remaining: " + game.getActions());
		mainGameWindow.getContentPane().add(daysRemainingLbl);
		
		JLabel dailyActionsLbl = new JLabel("Daily Actions:");
		dailyActionsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dailyActionsLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		dailyActionsLbl.setBounds(75, 132, 452, 35);
		dailyActionsLbl.setText(game.getPlayer().getName() + "'s Daily Actions:");
		mainGameWindow.getContentPane().add(dailyActionsLbl);
		
		JLabel noActionsErrorLbl = new JLabel("");
		noActionsErrorLbl.setForeground(Color.RED);
		noActionsErrorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		noActionsErrorLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noActionsErrorLbl.setBounds(75, 470, 452, 35);
		mainGameWindow.getContentPane().add(noActionsErrorLbl);
		
		JButton tendLandBtn = new JButton("Tend to Farm Land");
		tendLandBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					noActionsErrorLbl.setText("No daily actions remaining.");
				} else {
					String message = game.tendLand();
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);	
					game.mainGameLaunch();
					mainGameWindow.dispose();
				}
			}
		});
		tendLandBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendLandBtn.setBounds(75, 412, 452, 48);
		mainGameWindow.getContentPane().add(tendLandBtn);
		
		JButton farmStatusBtn = new JButton("View Farm Status");
		farmStatusBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		farmStatusBtn.setBounds(595, 180, 306, 131);
		mainGameWindow.getContentPane().add(farmStatusBtn);
		
		JButton visitStoreBtn = new JButton("Visit Store");
		visitStoreBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		visitStoreBtn.setBounds(595, 329, 306, 131);
		mainGameWindow.getContentPane().add(visitStoreBtn);
		
		JButton tendCropBtn = new JButton("Tend to a Crop");
		tendCropBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					noActionsErrorLbl.setText("No daily actions remaining.");
				} else {
					game.tendCropLaunch();
					mainGameWindow.dispose();
				}
				
			}
		});
		tendCropBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendCropBtn.setBounds(75, 180, 452, 48);
		mainGameWindow.getContentPane().add(tendCropBtn);
		
		JButton harvestCropBtn = new JButton("Harvest a Crop");
		harvestCropBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					noActionsErrorLbl.setText("No daily actions remaining.");
				} else {
					game.harvestCropLaunch();
					mainGameWindow.dispose();
				}
			}
		});
		harvestCropBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		harvestCropBtn.setBounds(75, 238, 452, 48);
		mainGameWindow.getContentPane().add(harvestCropBtn);
		
		JButton feedAnimalsBtn = new JButton("Feed Animals");
		feedAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					noActionsErrorLbl.setText("No daily actions remaining.");
				} else {
					game.feedAnimalLaunch();
					mainGameWindow.dispose();
				}
			}
		});
		feedAnimalsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedAnimalsBtn.setBounds(75, 296, 452, 48);
		mainGameWindow.getContentPane().add(feedAnimalsBtn);
		
		JButton playAnimalsBtn = new JButton("Play with Animals");
		playAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					noActionsErrorLbl.setText("No daily actions remaining.");
				} else if (game.getPlayerFarm().getAnimals().size() == 0) {
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, "You have no animals to play with.");	
				} else {
					String message = game.playWithAnimals();
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);					
					game.mainGameLaunch();
					mainGameWindow.dispose();
				}
			}
		});
		playAnimalsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playAnimalsBtn.setBounds(75, 354, 452, 48);
		mainGameWindow.getContentPane().add(playAnimalsBtn);
		
		
	}
}
