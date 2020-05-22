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

public class StartGameWindow {

	private JFrame farmSetupWindow;
	private JTextField playerNameText;
	private JTextField playerAgeText;
	private JTextField farmNameText;
	private JLabel lblSelectNumberOf;
	
	private Game game;	

	Farmer selectedFarmer = null;
	Farmer tempFarmer = null;
	Farm selectedFarm = null;
	Farm tempFarm = null;
    List<Farmer> farmerTypes = new ArrayList<Farmer>();
    List<Farm> farmTypes = new ArrayList<Farm>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGameWindow window = new StartGameWindow(new Game());
					window.farmSetupWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
	
	public void createFarmerList() {
		farmerTypes.add(new Farmer1());
		farmerTypes.add(new Farmer2());
	}
	
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
		
		JLabel welcomeLbl = new JLabel("Welcome to Farm Simulator!");
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		welcomeLbl.setBounds(10, 36, 976, 48);
		farmSetupWindow.getContentPane().add(welcomeLbl);
		
		JLabel playerNameLabel = new JLabel("Enter your name:");
		playerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameLabel.setBounds(21, 118, 221, 29);
		farmSetupWindow.getContentPane().add(playerNameLabel);
		
		playerNameText = new JTextField();
		playerNameText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameText.setBounds(211, 118, 246, 29);
		farmSetupWindow.getContentPane().add(playerNameText);
		playerNameText.setColumns(10);
		
		JLabel playerAgeLabel = new JLabel("Enter your age:");
		playerAgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerAgeLabel.setBounds(21, 157, 221, 29);
		farmSetupWindow.getContentPane().add(playerAgeLabel);
		
		playerAgeText = new JTextField();
		playerAgeText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerAgeText.setColumns(10);
		playerAgeText.setBounds(211, 157, 246, 29);
		farmSetupWindow.getContentPane().add(playerAgeText);
		
		JLabel lblEnterYourFarm = new JLabel("Enter your farm name:");
		lblEnterYourFarm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterYourFarm.setBounds(21, 196, 221, 29);
		farmSetupWindow.getContentPane().add(lblEnterYourFarm);
		
		farmNameText = new JTextField();
		farmNameText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmNameText.setColumns(10);
		farmNameText.setBounds(211, 196, 246, 29);
		farmSetupWindow.getContentPane().add(farmNameText);
		
		lblSelectNumberOf = new JLabel("Select number of days:");
		lblSelectNumberOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectNumberOf.setBounds(631, 118, 226, 29);
		farmSetupWindow.getContentPane().add(lblSelectNumberOf);
		
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
		
		JLabel lblSelectYourFarmer = new JLabel("Select farmer skill:");
		lblSelectYourFarmer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourFarmer.setBounds(22, 257, 221, 29);
		farmSetupWindow.getContentPane().add(lblSelectYourFarmer);
		
		JLabel lblSelectYourStarter = new JLabel("Select starter farm:");
		lblSelectYourStarter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourStarter.setBounds(333, 257, 221, 29);
		farmSetupWindow.getContentPane().add(lblSelectYourStarter);
		
		JLabel yourFarmerLabel = new JLabel("Your Farmer Skill:");
		yourFarmerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yourFarmerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yourFarmerLabel.setBounds(42, 433, 183, 48);
		farmSetupWindow.getContentPane().add(yourFarmerLabel);
		
		JLabel yourFarmLabel = new JLabel("Your Starter Farm:");
		yourFarmLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yourFarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yourFarmLabel.setBounds(356, 433, 172, 49);
		farmSetupWindow.getContentPane().add(yourFarmLabel);
		
		JLabel selectedFarmerLbl = new JLabel("");
		selectedFarmerLbl.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFarmerLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		selectedFarmerLbl.setBounds(158, 433, 165, 49);
		farmSetupWindow.getContentPane().add(selectedFarmerLbl);
		
		JLabel selectedFarmLbl = new JLabel("");
		selectedFarmLbl.setHorizontalAlignment(SwingConstants.LEFT);
		selectedFarmLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		selectedFarmLbl.setBounds(481, 433, 226, 49);
		farmSetupWindow.getContentPane().add(selectedFarmLbl);
		
		
		JLabel animalIncomeLbl = new JLabel("Animal Income");
		animalIncomeLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		animalIncomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalIncomeLbl.setBounds(580, 271, 139, 36);
		farmSetupWindow.getContentPane().add(animalIncomeLbl);
		
		JLabel lblCrop = new JLabel("Crop Income");
		lblCrop.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCrop.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrop.setBounds(700, 271, 139, 36);
		farmSetupWindow.getContentPane().add(lblCrop);
		
		JLabel lblStartingBalance = new JLabel("Starting Balance");
		lblStartingBalance.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStartingBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartingBalance.setBounds(824, 279, 139, 36);
		farmSetupWindow.getContentPane().add(lblStartingBalance);
		
		JLabel lblMultiplier = new JLabel("Multiplier");
		lblMultiplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMultiplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMultiplier.setBounds(584, 289, 139, 36);
		farmSetupWindow.getContentPane().add(lblMultiplier);
		
		JLabel lblMult2 = new JLabel("Multiplier");
		lblMult2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMult2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMult2.setBounds(703, 289, 139, 36);
		farmSetupWindow.getContentPane().add(lblMult2);
		
		JLabel skillLbl = new JLabel("Bonus");
		skillLbl.setHorizontalAlignment(SwingConstants.CENTER);
		skillLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		skillLbl.setBounds(151, 279, 139, 36);
		farmSetupWindow.getContentPane().add(skillLbl);
		
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
		
		JLabel selectedSkillLbl = new JLabel("");
		selectedSkillLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedSkillLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedSkillLbl.setBounds(145, 325, 156, 36);
		farmSetupWindow.getContentPane().add(selectedSkillLbl);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 245, 986, 15);
		farmSetupWindow.getContentPane().add(separator_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 99, 986, 15);
		farmSetupWindow.getContentPane().add(separator_1);
		
		
		JButton selectFarmerButton = new JButton("Select Farmer Skill");
		selectFarmerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tempFarmer != null) {
					selectedFarmer = tempFarmer;
					selectedFarmerLbl.setText(selectedFarmer.getSkill());
				}
			}
		});
		selectFarmerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectFarmerButton.setBounds(21, 405, 280, 29);
		farmSetupWindow.getContentPane().add(selectFarmerButton);
		
		JButton btnSelectStarterFarm = new JButton("Select Starter Farm");
		btnSelectStarterFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tempFarm != null) {
					selectedFarm = tempFarm;
					selectedFarmLbl.setText(selectedFarm.getType());
				}
			}
		});
		btnSelectStarterFarm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelectStarterFarm.setBounds(333, 405, 636, 29);
		farmSetupWindow.getContentPane().add(btnSelectStarterFarm);
		
		JLabel startErrorLabel = new JLabel("");
		startErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startErrorLabel.setForeground(Color.RED);
		startErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		startErrorLabel.setBounds(584, 509, 246, 29);
		farmSetupWindow.getContentPane().add(startErrorLabel);
		
		JButton startGameButton = new JButton("Start");
		startGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ageText = playerAgeText.getText();
				try {
					Integer.parseInt(ageText); 
				}catch(NumberFormatException f) {
					ageText = null;
				} 
				if (playerNameText.getText().isEmpty()) {
					startErrorLabel.setText("Please enter name.");
				} else if (ageText == null) {
					startErrorLabel.setText("Please enter valid age.");
				} else if (farmNameText.getText().isEmpty()) {
					startErrorLabel.setText("Please enter farm name.");
				} else if (selectedFarmer == null) {
					startErrorLabel.setText("Please select farmer skill.");
				} else if (selectedFarm == null) {
					startErrorLabel.setText("Please select starter farm.");
				} else {
					startErrorLabel.setText("");
					int age = Integer.parseInt(ageText);
					game.setPlayer(selectedFarmer, playerNameText.getText(), age);
					game.setPlayerFarm(selectedFarm, farmNameText.getText());
					game.setDaysRemaining(daysRemainingSlider.getValue());
					game.setStore();
					game.mainGameLaunch();
					farmSetupWindow.dispose();
					String message = "Welcome to Farm Simulator!" + Game.nln;
					message += "Click View Farm Status to view the Crops and Animals owned by the Farm." + Game.nln;
					message += "Purchase Crops, Items, and Animals from the Store." + Game.nln;
					message += "Crops can be grown, tended to with Items, harvested, and sold for a money bonus." + Game.nln;
					message += "Animals can be fed with Items, played with, and can return a daily money bonus." + Game.nln;
					message += "Tending to aspects of the farm costs one daily action." + Game.nln;
					message += "You have a maximum of 2 daily actions per day." + Game.nln;
					message += "Maximise your final score by farming more Crops and Animals.";
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);	
					
					
				}
			}
		});
		startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		startGameButton.setBounds(831, 509, 131, 29);
		farmSetupWindow.getContentPane().add(startGameButton);
		
		JButton farmer2Btn = new JButton(farmerTypes.get(1).getSkill());
		farmer2Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farmer2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarmer = farmerTypes.get(1);
				selectedSkillLbl.setText("+" + tempFarmer.getAnimalBonus() + " Animal Health");
			}
		});
		farmer2Btn.setBounds(21, 347, 113, 45);
		farmSetupWindow.getContentPane().add(farmer2Btn);
		
		JButton farm1Btn = new JButton(farmTypes.get(0).getType());
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
		
		JButton farm3Btn = new JButton(farmTypes.get(2).getType());
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
		
		JButton farmer1Btn = new JButton(farmerTypes.get(0).getSkill());
		farmer1Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farmer1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarmer = farmerTypes.get(0);
				selectedSkillLbl.setText("+" + tempFarmer.getCropBonus() + " Crop Growth");
				
			}
		});
		farmer1Btn.setBounds(21, 296, 113, 45);
		farmSetupWindow.getContentPane().add(farmer1Btn);
		
		JButton farm2Btn = new JButton(farmTypes.get(1).getType());
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
		
		JButton farm4Btn = new JButton(farmTypes.get(3).getType());
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
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(316, 245, 22, 236);
		farmSetupWindow.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(500, 100, 22, 146);
		farmSetupWindow.getContentPane().add(separator_4);
		
		JLabel lblInstructions = new JLabel("Enter details and press Start.");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInstructions.setBounds(279, 509, 381, 29);
		farmSetupWindow.getContentPane().add(lblInstructions);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 481, 986, 29);
		farmSetupWindow.getContentPane().add(separator);
		
		
		
		
		
		
		
		
	}
}
