package main;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

/**
 * Start of game GUI window
 * @author USER
 *
 */

public class StartGameWindow {

	private JFrame farmSetupWindow;
	
	/**
	 * Text fields
	 */
	private JTextField playerNameText;
	private JTextField playerAgeText;
	private JTextField farmNameText;

	
	/** 
	 * Current state game variable 
	 */
	private Game game;	
	
	/**
	 * Farmer type currently displayed
	 */
    Farmer tempFarmer = null;
	/**
	 * Farm type currently displayed
	 */
	Farm tempFarm = null;
	/**
	 * Farmer type selected by player for game
	 */
	Farmer selectedFarmer = null;
	/**
	 * Farm type selected by player for game
	 */
	Farm selectedFarm = null;
	/**
	 * Farmer types player can choose from
	 */
    List<Farmer> farmerTypes = new ArrayList<Farmer>();
    /**
     * Farm types player can choose from
     */
    List<Farm> farmTypes = new ArrayList<Farm>();


	/**
	 * Create the application.
	 */
	public StartGameWindow(Game game) { // (Game game)
		this.game = game;
		createFarmerList();
		createFarmList();
		initialize();
		farmSetupWindow.setVisible(true);
	}
	
	/**
	 * Populates list with farmer types
	 */
	public void createFarmerList() {
		farmerTypes.add(new Farmer1());
		farmerTypes.add(new Farmer2());
	}
	
	/**
	 * Populates list with farm types
	 */
	public void createFarmList() {
		farmTypes.add(new Farm1());
		farmTypes.add(new Farm2());
		farmTypes.add(new Farm3());
		farmTypes.add(new Farm4());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		farmSetupWindow = new JFrame();
		farmSetupWindow.setResizable(false);
		farmSetupWindow.setTitle("Farm Setup");
		farmSetupWindow.setBounds(100, 100, 1000, 600);
		farmSetupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		farmSetupWindow.getContentPane().setLayout(null);
		
		/**
		 * Header labels
		 */
		JLabel welcomeLbl = new JLabel("Welcome to Farm Simulator!");
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		welcomeLbl.setBounds(10, 36, 976, 48);
		farmSetupWindow.getContentPane().add(welcomeLbl);
		
		JLabel detailsLbl = new JLabel("Enter details and press Start.");
		detailsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		detailsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		detailsLbl.setBounds(279, 519, 381, 29);
		farmSetupWindow.getContentPane().add(detailsLbl);
		
		JLabel helpLbl = new JLabel("Press Help for setup instructions.");
		helpLbl.setHorizontalAlignment(SwingConstants.CENTER);
		helpLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		helpLbl.setBounds(279, 496, 381, 29);
		farmSetupWindow.getContentPane().add(helpLbl);
		
		
		JLabel playerNameLbl = new JLabel("Enter your name:");
		playerNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameLbl.setBounds(21, 118, 221, 29);
		farmSetupWindow.getContentPane().add(playerNameLbl);
		
		JLabel playerAgeLbl = new JLabel("Enter your age:");
		playerAgeLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerAgeLbl.setBounds(21, 157, 221, 29);
		farmSetupWindow.getContentPane().add(playerAgeLbl);
		
		JLabel farmNameLbl = new JLabel("Enter your farm name:");
		farmNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmNameLbl.setBounds(21, 196, 221, 29);
		farmSetupWindow.getContentPane().add(farmNameLbl);
		
		JLabel daysLbl = new JLabel("Select in-game number of days:");
		daysLbl.setHorizontalAlignment(SwingConstants.CENTER);
		daysLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		daysLbl.setBounds(631, 118, 226, 29);
		farmSetupWindow.getContentPane().add(daysLbl);
		
		JLabel selectFarmerLbl = new JLabel("Choose your farmer skill:");
		selectFarmerLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		selectFarmerLbl.setBounds(22, 257, 246, 29);
		farmSetupWindow.getContentPane().add(selectFarmerLbl);
		
		JLabel selectFarmLbl = new JLabel("Choose your starting farm:");
		selectFarmLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		selectFarmLbl.setBounds(333, 257, 266, 29);
		farmSetupWindow.getContentPane().add(selectFarmLbl);
		
		JLabel yourFarmerLbl = new JLabel("Your Farmer Skill:");
		yourFarmerLbl.setHorizontalAlignment(SwingConstants.LEFT);
		yourFarmerLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yourFarmerLbl.setBounds(21, 433, 204, 48);
		farmSetupWindow.getContentPane().add(yourFarmerLbl);
		
		JLabel yourFarmLbl = new JLabel("Your Starter Farm:");
		yourFarmLbl.setHorizontalAlignment(SwingConstants.LEFT);
		yourFarmLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yourFarmLbl.setBounds(333, 433, 195, 49);
		farmSetupWindow.getContentPane().add(yourFarmLbl);
		
		JLabel animalIncomeLbl = new JLabel("Animal Income");
		animalIncomeLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		animalIncomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalIncomeLbl.setBounds(580, 271, 139, 36);
		farmSetupWindow.getContentPane().add(animalIncomeLbl);
		
		JLabel cropIncLbl = new JLabel("Crop Income");
		cropIncLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		cropIncLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropIncLbl.setBounds(700, 271, 139, 36);
		farmSetupWindow.getContentPane().add(cropIncLbl);
		
		JLabel balanceLbl = new JLabel("Starting Balance");
		balanceLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		balanceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		balanceLbl.setBounds(824, 279, 139, 36);
		farmSetupWindow.getContentPane().add(balanceLbl);
		
		JLabel multLbl = new JLabel("Multiplier");
		multLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		multLbl.setHorizontalAlignment(SwingConstants.CENTER);
		multLbl.setBounds(584, 289, 139, 36);
		farmSetupWindow.getContentPane().add(multLbl);
		
		JLabel mult2Lbl = new JLabel("Multiplier");
		mult2Lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		mult2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		mult2Lbl.setBounds(703, 289, 139, 36);
		farmSetupWindow.getContentPane().add(mult2Lbl);
		
		JLabel skillLbl = new JLabel("Skill Bonus");
		skillLbl.setHorizontalAlignment(SwingConstants.CENTER);
		skillLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		skillLbl.setBounds(151, 279, 139, 36);
		farmSetupWindow.getContentPane().add(skillLbl);
		/**
		 * End of header labels
		 */
		
		
		
		/**
		 * Text field for player to enter name
		 */
		playerNameText = new JTextField();
		playerNameText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameText.setBounds(211, 118, 246, 29);
		farmSetupWindow.getContentPane().add(playerNameText);
		playerNameText.setColumns(10);
		
		/**
		 * Text field for player to enter age - must be integer
		 */
		playerAgeText = new JTextField();
		playerAgeText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerAgeText.setColumns(10);
		playerAgeText.setBounds(211, 157, 246, 29);
		farmSetupWindow.getContentPane().add(playerAgeText);
		
		
		/**
		 * Text field for player to enter name of farm
		 */
		farmNameText = new JTextField();
		farmNameText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmNameText.setColumns(10);
		farmNameText.setBounds(211, 196, 246, 29);
		farmSetupWindow.getContentPane().add(farmNameText);
		
		
		/**
		 * Slider for player to select number of in-game days game will last
		 */
		JSlider daysRemainingSlider = new JSlider();
		daysRemainingSlider.setFont(new Font("Tahoma", Font.PLAIN, 18));
		daysRemainingSlider.setSnapToTicks(true);
		daysRemainingSlider.setValue(5);
		daysRemainingSlider.setPaintLabels(true);
		daysRemainingSlider.setPaintTicks(true);
		daysRemainingSlider.setMajorTickSpacing(1);
		daysRemainingSlider.setMinimum(5);
		daysRemainingSlider.setMaximum(10);
		daysRemainingSlider.setBounds(563, 157, 362, 64);
		farmSetupWindow.getContentPane().add(daysRemainingSlider);
		
		
		/**
		 * Displays player selected farmer held in selectedFarmer variable
		 */
		JLabel selectedFarmerLbl = new JLabel("");
		selectedFarmerLbl.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFarmerLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		selectedFarmerLbl.setBounds(136, 433, 189, 49);
		farmSetupWindow.getContentPane().add(selectedFarmerLbl);
		
		/**
		 * Displays player selected farm held in selectedFarmer variable
		 */
		JLabel selectedFarmLbl = new JLabel("");
		selectedFarmLbl.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFarmLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		selectedFarmLbl.setBounds(451, 433, 256, 49);
		farmSetupWindow.getContentPane().add(selectedFarmLbl);

		
		/**
		 * Labels display the animalMoneyBonus, cropMoneyBonus, and money attributes of the Farm held in variable tempFarm
		 * tempFarm is the Farm whose attributes the player is currently viewing
		 */
		JLabel selectedAnimalMultLbl = new JLabel("");
		selectedAnimalMultLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedAnimalMultLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedAnimalMultLbl.setBounds(580, 325, 139, 36);
		farmSetupWindow.getContentPane().add(selectedAnimalMultLbl);
		
		
		JLabel selectedCropMultLbl = new JLabel("");
		selectedCropMultLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCropMultLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedCropMultLbl.setBounds(703, 325, 139, 36);
		farmSetupWindow.getContentPane().add(selectedCropMultLbl);
		
		JLabel selectedBankLbl = new JLabel("");
		selectedBankLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedBankLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedBankLbl.setBounds(825, 325, 139, 36);
		farmSetupWindow.getContentPane().add(selectedBankLbl);
		/**
		 * End of tempFarm labels
		 */
		
		
		
		/**
		 * Label displays the relevant bonus attribute for the Farmer held in the tempFarmer variable
		 * tempFarmer is the Farmer whose skill the player is currently viewing
		 */
		JLabel selectedSkillLbl = new JLabel("");
		selectedSkillLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedSkillLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedSkillLbl.setBounds(145, 325, 156, 36);
		farmSetupWindow.getContentPane().add(selectedSkillLbl);
		
		/**
		 * Alerts player if a required field is missing
		 */
		JLabel errorLbl = new JLabel("");
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorLbl.setForeground(Color.RED);
		errorLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		errorLbl.setBounds(584, 509, 246, 29);
		farmSetupWindow.getContentPane().add(errorLbl);
		
		/**
		 * Display separators
		 */
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 245, 986, 15);
		farmSetupWindow.getContentPane().add(separator_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 99, 986, 15);
		farmSetupWindow.getContentPane().add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(316, 245, 22, 236);
		farmSetupWindow.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(500, 100, 22, 146);
		farmSetupWindow.getContentPane().add(separator_4);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 481, 986, 15);
		farmSetupWindow.getContentPane().add(separator);

		/**
		 * Buttons for each of the two Farmer types
		 * Clicking one button sets tempFarmer variable to the Farmer type clicked 
		 * The relevant bonus attribute of that farmer is displayed in the skill label
		 */
		JButton farmer1Btn = new JButton("Skill 1"); //(farmerTypes.get(0).getSkill());
		farmer1Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farmer1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarmer = farmerTypes.get(0);
				selectedSkillLbl.setText("+" + tempFarmer.getCropBonus() + " Crop Growth");
				
			}
		});
		farmer1Btn.setBounds(21, 296, 113, 45);
		farmSetupWindow.getContentPane().add(farmer1Btn);
		
		JButton farmer2Btn = new JButton("Skill 2"); //(farmerTypes.get(1).getSkill());
		farmer2Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farmer2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarmer = farmerTypes.get(1);
				selectedSkillLbl.setText("+" + tempFarmer.getAnimalBonus() + " Animal Care");
			}
		});
		farmer2Btn.setBounds(21, 347, 113, 45);
		farmSetupWindow.getContentPane().add(farmer2Btn);
		/**
		 * End of Farmer buttons
		 */
		
		
		/**
		 * Buttons for each of the four Farm types
		 * Clicking one button sets tempFarm variable to the Farm type clicked 
		 * Each of the attributes of that farm are displayed in the attribute labels
		 */
		JButton farm1Btn = new JButton("Farm 1"); //(farmTypes.get(0).getType());
		farm1Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farm1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarm = farmTypes.get(0);
				selectedAnimalMultLbl.setText("x" + tempFarm.getAnimalMoneyBonus());
				selectedCropMultLbl.setText("x" + tempFarm.getCropMoneyBonus());
				selectedBankLbl.setText("$" + tempFarm.getMoney());
			}
		});
		farm1Btn.setBounds(333, 296, 113, 45);
		farmSetupWindow.getContentPane().add(farm1Btn);
		
		JButton farm2Btn = new JButton("Farm 2"); //(farmTypes.get(1).getType());
		farm2Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farm2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarm = farmTypes.get(1);
				selectedAnimalMultLbl.setText("x" + tempFarm.getAnimalMoneyBonus());
				selectedCropMultLbl.setText("x" + tempFarm.getCropMoneyBonus());
				selectedBankLbl.setText("$" + tempFarm.getMoney());
			}
		});
		farm2Btn.setBounds(456, 296, 113, 45);
		farmSetupWindow.getContentPane().add(farm2Btn);
		
		JButton farm3Btn = new JButton("Farm 3"); //(farmTypes.get(2).getType());
		farm3Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farm3Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarm = farmTypes.get(2);
				selectedAnimalMultLbl.setText("x" + tempFarm.getAnimalMoneyBonus());
				selectedCropMultLbl.setText("x" + tempFarm.getCropMoneyBonus());
				selectedBankLbl.setText("$" + tempFarm.getMoney());
			}
		});
		farm3Btn.setBounds(333, 347, 113, 45);
		farmSetupWindow.getContentPane().add(farm3Btn);
		
		JButton farm4Btn = new JButton("Farm 4"); //(farmTypes.get(3).getType());
		farm4Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farm4Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarm = farmTypes.get(3);
				selectedAnimalMultLbl.setText("x" + tempFarm.getAnimalMoneyBonus());
				selectedCropMultLbl.setText("x" + tempFarm.getCropMoneyBonus());
				selectedBankLbl.setText("$" + tempFarm.getMoney());
			}
		});
		farm4Btn.setBounds(456, 347, 113, 45);
		farmSetupWindow.getContentPane().add(farm4Btn);
		/**
		 * End of Farm buttons
		 */
		
		
		
		/**
		 * Sets selectedFarmer variable to Farmer held in tempFarmer
		 */
		JButton selectFarmerBtn = new JButton("Select Farmer Skill");
		selectFarmerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tempFarmer != null) {
					selectedFarmer = tempFarmer;
					selectedFarmerLbl.setText("Skill " + (farmerTypes.indexOf(selectedFarmer)+1) + ": " + selectedFarmer.getSkill());
					errorLbl.setText("");
				}
			}
		});
		selectFarmerBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectFarmerBtn.setBounds(21, 405, 280, 29);
		farmSetupWindow.getContentPane().add(selectFarmerBtn);
		
		/**
		 * Sets selectedFarm variable to Farmer held in tempFarm
		 */
		JButton selectFarmBtn = new JButton("Select Starter Farm");
		selectFarmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tempFarm != null) {
					selectedFarm = tempFarm;
					selectedFarmLbl.setText("Farm " + (farmTypes.indexOf(selectedFarm)+1) + ": " + selectedFarm.getType());
					errorLbl.setText("");
				}
			}
		});
		selectFarmBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectFarmBtn.setBounds(333, 405, 636, 29);
		farmSetupWindow.getContentPane().add(selectFarmBtn);
		
		
		/**
		 * Checks that no required fields are empty and checks that the age entered is an integer
		 * Sets errorLbl text to notify player if there are missing field entries
		 * Otherwise, sets attributes of game object and launches MainGameWindow to start game
		 * Opens JOptionPane to display instructions to player
		 */
		JButton startGameBtn = new JButton("Start");
		startGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ageText = playerAgeText.getText();
				try {
					Integer.parseInt(ageText); 
				}catch(NumberFormatException f) {
					ageText = null;
				} 
				if (playerNameText.getText().isEmpty()) {
					errorLbl.setText("Please enter name.");
				} else if (ageText == null) {
					errorLbl.setText("Please enter valid age.");
				} else if (farmNameText.getText().isEmpty()) {
					errorLbl.setText("Please enter farm name.");
				} else if (selectedFarmer == null) {
					errorLbl.setText("Please select farmer skill.");
				} else if (selectedFarm == null) {
					errorLbl.setText("Please select starter farm.");
				} else {
					errorLbl.setText("");
					int age = Integer.parseInt(ageText);
					game.setPlayer(selectedFarmer, playerNameText.getText(), age);
					game.setPlayerFarm(selectedFarm, farmNameText.getText());
					game.setDaysRemaining(daysRemainingSlider.getValue());
					game.setStore();
					game.mainGameLaunch();
					farmSetupWindow.dispose();
					String message = "Welcome to Farm Simulator!" + Game.nln + Game.nln;
					message += "Click View Farm Status to view the Crops and Animals owned by the Farm." + Game.nln;
					message += "Purchase Crops, Items, and Animals from the Store." + Game.nln;
					message += "Crops can be grown, tended to with Items, harvested, and sold for a money bonus." + Game.nln;
					message += "Animals can be fed with Items, played with, and can return a daily money bonus." + Game.nln;
					message += "Animals will not survive without regular feeding and play." + Game.nln + Game.nln;
					message += "Tending to aspects of the farm will cost one daily action." + Game.nln;
					message += "You have a maximum of 2 daily actions per day. Click Next Day to progress for more actions." + Game.nln;
					message += "Maximise your final score by farming more Crops and Animals." + Game.nln + Game.nln;
					message += "Click the Help button in each window to view more farming tips.";
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);	
					
					
				}
			}
		});
		startGameBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		startGameBtn.setBounds(831, 509, 131, 29);
		farmSetupWindow.getContentPane().add(startGameBtn);
		
		
		
		
		
		
		
		
		/**
		 * Help button displays JOptionPane with instructions for setup
		 */
		JButton instructionsBtn = new JButton("Help");
		instructionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLbl.setText("");
				String message = "Enter your name, age and farm name." + Game.nln;
				message += "Select the number of in-game days you would like the simulation to last." + Game.nln;
				message += "Choose your farmer skill. Farmer skills improve your crop growing or animal care ability." + Game.nln;
				message += "Choose your farm. Each farm has different starter bonuses and starting accounts." + Game.nln;
				message += "You can still grow animals and crops on any type of farm." + Game.nln;
				message += "You can improve your bonuses as you progress through the game." + Game.nln;
				message += "Game-play instructions are available after setup." + Game.nln;
				message += "Enter all required fields and press Start.";
				JOptionPane frame = new JOptionPane();
				JOptionPane.showMessageDialog(frame, message);	
			}
		});
		instructionsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsBtn.setBounds(892, 15, 75, 29);
		farmSetupWindow.getContentPane().add(instructionsBtn);
		
		
		
		
		
		
		
		
		
		
	}
}
