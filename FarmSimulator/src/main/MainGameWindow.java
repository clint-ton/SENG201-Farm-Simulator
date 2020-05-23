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
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;


/**
 * Main game GUI window
 * @author jke99, cjw237
 *
 */
public class MainGameWindow {

	private JFrame mainGameWindow;
	
	/**
	 * Current state game variable
	 */
	private Game game;



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
		
		
		/**
		 * Header label
		 */
		JLabel farmTitlelbl = new JLabel("");
		farmTitlelbl.setBounds(10, 37, 976, 48);
		farmTitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		farmTitlelbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		farmTitlelbl.setText(game.getPlayerFarm().getName() + " Manager");
		mainGameWindow.getContentPane().add(farmTitlelbl);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(423, 182, 1, 278);
		mainGameWindow.getContentPane().add(separator);
		
		/**
		 * Label in-game days progressed
		 */
		JLabel dayLbl = new JLabel("Day: " + game.getDay());
		dayLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayLbl.setBounds(93, 470, 348, 29);
		mainGameWindow.getContentPane().add(dayLbl);
		
		/**
		 * Label for in-game days remaining
		 */
		JLabel daysRemainingLbl = new JLabel("Days Remaining: " + game.getDaysRemaining());
		daysRemainingLbl.setHorizontalAlignment(SwingConstants.LEFT);
		daysRemainingLbl.setBounds(93, 494, 338, 29);
		daysRemainingLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mainGameWindow.getContentPane().add(daysRemainingLbl);
		
		/**
		 * Player farm account balance
		 */
		JLabel accBalanceLbl = new JLabel("Account Balance: $" + game.getPlayerFarm().getMoney());
		accBalanceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		accBalanceLbl.setBounds(374, 519, 348, 29);
		mainGameWindow.getContentPane().add(accBalanceLbl);
		
		/**
		 * Label number of player daily actions remaining
		 */
		JLabel actionsRemainingLbl = new JLabel("Daily Actions Remaining: " + game.getActions());
		actionsRemainingLbl.setHorizontalAlignment(SwingConstants.LEFT);
		actionsRemainingLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		actionsRemainingLbl.setBounds(93, 519, 322, 29);
		mainGameWindow.getContentPane().add(actionsRemainingLbl);
		
		/**
		 * Actions header
		 */
		JLabel dailyActionsLbl = new JLabel("Daily Actions:");
		dailyActionsLbl.setBorder(null);
		dailyActionsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dailyActionsLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		dailyActionsLbl.setBounds(449, 132, 452, 35);
		dailyActionsLbl.setText(game.getPlayer().getName() + "'s Daily Actions:");
		mainGameWindow.getContentPane().add(dailyActionsLbl);
		
		/**
		 * Alerts player if action attempted but no actions remaining
		 */
		JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		errorLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLbl.setBounds(629, 464, 272, 35);
		mainGameWindow.getContentPane().add(errorLbl);
		
		
		/**
		 * Opens StatusWindow to view player farm status
		 */
		JButton farmStatusBtn = new JButton("View Farm Status");
		farmStatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.statusLaunch();
				mainGameWindow.dispose();
			}
		});
		farmStatusBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		farmStatusBtn.setBounds(93, 180, 306, 131);
		mainGameWindow.getContentPane().add(farmStatusBtn);
		
		/**
		 * Opens StoreWindow to visit farm store
		 */
		JButton visitStoreBtn = new JButton("Visit Store");
		visitStoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.storeLaunch();
				mainGameWindow.dispose();
			}
		});
		visitStoreBtn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		visitStoreBtn.setBounds(93, 329, 306, 131);
		mainGameWindow.getContentPane().add(visitStoreBtn);
		
		/**
		 * Calls method in Game class to tend to farm land
		 * Opens JOptionPane displaying feedback from method called
		 * Sets errorLbl text to notify player if no actions remaining
		 */
		JButton tendLandBtn = new JButton("Tend to Farm Land");
		tendLandBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					errorLbl.setText("No daily actions remaining.");
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
		tendLandBtn.setBounds(449, 412, 452, 48);
		mainGameWindow.getContentPane().add(tendLandBtn);
		
		/**
		 * Opens TendCropWindow to tend to player crop
		 * Sets errorLbl text to notify player if no actions remaining
		 */
		JButton tendCropBtn = new JButton("Tend to a Crop");
		tendCropBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					errorLbl.setText("No daily actions remaining.");
				} else {
					game.tendCropLaunch();
					mainGameWindow.dispose();
				}
			}
		});
		tendCropBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendCropBtn.setBounds(449, 180, 452, 48);
		mainGameWindow.getContentPane().add(tendCropBtn);
		
		/**
		 * Opens HarvestCropWindow to harvest player crop
		 * Sets errorLbl text to notify player if no actions remaining
		 */
		JButton harvestCropBtn = new JButton("Harvest a Crop");
		harvestCropBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					errorLbl.setText("No daily actions remaining.");
				} else {
					game.harvestCropLaunch();
					mainGameWindow.dispose();
				}
			}
		});
		harvestCropBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		harvestCropBtn.setBounds(449, 238, 452, 48);
		mainGameWindow.getContentPane().add(harvestCropBtn);
		
		
		/**
		 * Opens FeedAnimalsWindow to feed player animals
		 * Sets errorLbl text to notify player if no actions remaining
		 */
		JButton feedAnimalsBtn = new JButton("Feed Animals");
		feedAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					errorLbl.setText("No daily actions remaining.");
				} else {
					game.feedAnimalLaunch();
					mainGameWindow.dispose();
				}
			}
		});
		feedAnimalsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedAnimalsBtn.setBounds(449, 296, 452, 48);
		mainGameWindow.getContentPane().add(feedAnimalsBtn);
		
		
		/**
		 * Calls method in Game class to play with player animals
		 * Opens JOptionPane to display feedback from method called
		 * Sets errorLbl text to notify player if no actions remaining
		 */
		JButton playAnimalsBtn = new JButton("Play with Animals");
		playAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getActions() == 0) {
					errorLbl.setText("No daily actions remaining.");
				} else if (game.getPlayerFarm().getAnimals().size() == 0) {
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, "You have no animals to play with." + Game.nln + "Purchase animals from the store.");	
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
		playAnimalsBtn.setBounds(449, 354, 452, 48);
		mainGameWindow.getContentPane().add(playAnimalsBtn);
		
		
		
		/**
		 * Button closes current MainGameWindow
		 * Opens new MainGameWindow for next day with updated game state if daysRemaining Game variable is greater than 0
		 * Opens EndGameWindow to finish game if daysRemaining Game variable = 0
		 * JOptionPane gets user choice to progress to next day if actions Game variable is not 0
		 */
		JButton nextDayButton = new JButton("Next Day");
		if (game.getDaysRemaining() == 1) {
			nextDayButton.setText("Finish Game");
		}
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = -1;
				if (game.getActions() > 0) {
					String prompt = "Are you sure? You still have " + game.getActions() + " daily actions remaining.";
					JOptionPane nextDayChoose = new JOptionPane();
					choice = JOptionPane.showConfirmDialog(nextDayChoose, prompt, "Next Day", JOptionPane.YES_NO_OPTION);
				}					
				if ((choice == -1) || (choice == JOptionPane.YES_OPTION)) {
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
						game.endGameLaunch();
						mainGameWindow.dispose();
					}
				}
			}
			
		});
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nextDayButton.setBounds(795, 519, 172, 29);
		mainGameWindow.getContentPane().add(nextDayButton);
		
		/**
		 * Help button displays JOptionPane with instructions and descriptions for the current window
		 */
		JButton instructionsBtn = new JButton("Help");
		instructionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLbl.setText("");
				String message = "Click View Farm Status to view the Crops and Animals owned by the Farm." + Game.nln;
				message += "Purchase Crops, Items, and Animals from the Store." + Game.nln;
				message += "Crops can be grown, tended to with Items, harvested, and sold for a money bonus." + Game.nln;
				message += "Animals can be fed with Items, played with, and can return a daily money bonus." + Game.nln;
				message += "Animals will not survive without regular feeding and play." + Game.nln + Game.nln;
				message += "Tending to aspects of the farm will cost one daily action." + Game.nln;
				message += "You have a maximum of 2 daily actions per day. Click Next Day to progress for more actions." + Game.nln;
				message += "Maximise your final score by farming more Crops and Animals.";
				JOptionPane frame = new JOptionPane();
				JOptionPane.showMessageDialog(frame, message);	
			}
		});
		instructionsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsBtn.setBounds(892, 15, 75, 29);
		mainGameWindow.getContentPane().add(instructionsBtn);
		
		
		
		
	}
}
