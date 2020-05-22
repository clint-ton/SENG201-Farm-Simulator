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
		lblEnterYourFarm.setBounds(21, 195, 221, 29);
		farmSetupWindow.getContentPane().add(lblEnterYourFarm);
		
		farmNameText = new JTextField();
		farmNameText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmNameText.setColumns(10);
		farmNameText.setBounds(211, 196, 246, 29);
		farmSetupWindow.getContentPane().add(farmNameText);
		
		lblSelectNumberOf = new JLabel("Select number of days:");
		lblSelectNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectNumberOf.setBounds(639, 118, 226, 29);
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
		daysRemainingSlider.setBounds(540, 157, 362, 64);
		farmSetupWindow.getContentPane().add(daysRemainingSlider);
		
		JLabel lblSelectYourFarmer = new JLabel("Select farmer skill:");
		lblSelectYourFarmer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourFarmer.setBounds(21, 268, 221, 29);
		farmSetupWindow.getContentPane().add(lblSelectYourFarmer);
		
		JLabel lblSelectYourStarter = new JLabel("Select starter farm:");
		lblSelectYourStarter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourStarter.setBounds(318, 268, 221, 29);
		farmSetupWindow.getContentPane().add(lblSelectYourStarter);
		
		JLabel selectedFarmerLabel = new JLabel("Your Farmer Skill: ");
		selectedFarmerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedFarmerLabel.setBounds(489, 480, 292, 36);
		farmSetupWindow.getContentPane().add(selectedFarmerLabel);
		
		JLabel selectedFarmLabel = new JLabel("Your Starter Farm: ");
		selectedFarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedFarmLabel.setBounds(489, 509, 341, 36);
		farmSetupWindow.getContentPane().add(selectedFarmLabel);
		
		
		JLabel animalIncomeLbl = new JLabel("Animal Income");
		animalIncomeLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		animalIncomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalIncomeLbl.setBounds(564, 282, 139, 36);
		farmSetupWindow.getContentPane().add(animalIncomeLbl);
		
		JLabel lblCrop = new JLabel("Crop Income");
		lblCrop.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCrop.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrop.setBounds(691, 282, 139, 36);
		farmSetupWindow.getContentPane().add(lblCrop);
		
		JLabel lblStartingBalance = new JLabel("Starting Balance");
		lblStartingBalance.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStartingBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartingBalance.setBounds(823, 290, 139, 36);
		farmSetupWindow.getContentPane().add(lblStartingBalance);
		
		JLabel lblMultiplier = new JLabel("Multiplier");
		lblMultiplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMultiplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblMultiplier.setBounds(568, 300, 139, 36);
		farmSetupWindow.getContentPane().add(lblMultiplier);
		
		JLabel lblMult2 = new JLabel("Multiplier");
		lblMult2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMult2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMult2.setBounds(693, 300, 139, 36);
		farmSetupWindow.getContentPane().add(lblMult2);
		
		JLabel skillLbl = new JLabel("Bonus");
		skillLbl.setHorizontalAlignment(SwingConstants.CENTER);
		skillLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		skillLbl.setBounds(150, 290, 139, 36);
		farmSetupWindow.getContentPane().add(skillLbl);
		
		JLabel selectedAnimalMultLbl = new JLabel("");
		selectedAnimalMultLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedAnimalMultLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedAnimalMultLbl.setBounds(564, 346, 139, 36);
		farmSetupWindow.getContentPane().add(selectedAnimalMultLbl);
		
		JLabel selectedCropMultLbl = new JLabel("");
		selectedCropMultLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCropMultLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedCropMultLbl.setBounds(693, 346, 139, 36);
		farmSetupWindow.getContentPane().add(selectedCropMultLbl);
		
		JLabel selectedBankLbl = new JLabel("");
		selectedBankLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedBankLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedBankLbl.setBounds(824, 346, 139, 36);
		farmSetupWindow.getContentPane().add(selectedBankLbl);
		
		JLabel selectedSkillLbl = new JLabel("");
		selectedSkillLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedSkillLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		selectedSkillLbl.setBounds(143, 346, 156, 36);
		farmSetupWindow.getContentPane().add(selectedSkillLbl);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 245, 976, 15);
		farmSetupWindow.getContentPane().add(separator_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 462, 976, 29);
		farmSetupWindow.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 99, 976, 15);
		farmSetupWindow.getContentPane().add(separator_1);
		
		
		JButton selectFarmerButton = new JButton("Select Farmer Skill");
		selectFarmerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tempFarmer != null) {
					selectedFarmer = tempFarmer;
					selectedFarmerLabel.setText("Your Farmer Skill: " + selectedFarmer.getSkill());
				}
			}
		});
		selectFarmerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectFarmerButton.setBounds(143, 413, 156, 29);
		farmSetupWindow.getContentPane().add(selectFarmerButton);
		
		JButton btnSelectStarterFarm = new JButton("Select Starter Farm");
		btnSelectStarterFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tempFarm != null) {
					selectedFarm = tempFarm;
					selectedFarmLabel.setText("Your Starter Farm: " + selectedFarm.getType());
				}
			}
		});
		btnSelectStarterFarm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelectStarterFarm.setBounds(680, 413, 156, 29);
		farmSetupWindow.getContentPane().add(btnSelectStarterFarm);
		
		JLabel startErrorLabel = new JLabel("");
		startErrorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		startErrorLabel.setForeground(Color.RED);
		startErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startErrorLabel.setBounds(703, 470, 266, 36);
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
				}
			}
		});
		startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		startGameButton.setBounds(840, 516, 131, 29);
		farmSetupWindow.getContentPane().add(startGameButton);
		
		JButton farmer2Btn = new JButton(farmerTypes.get(1).getSkill());
		farmer2Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farmer2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarmer = farmerTypes.get(1);
				selectedSkillLbl.setText("+" + tempFarmer.getAnimalBonus() + " Animal Health");
			}
		});
		farmer2Btn.setBounds(20, 358, 113, 45);
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
		farm1Btn.setBounds(318, 307, 113, 45);
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
		farm3Btn.setBounds(318, 358, 113, 45);
		farmSetupWindow.getContentPane().add(farm3Btn);
		
		JButton farmer1Btn = new JButton(farmerTypes.get(0).getSkill());
		farmer1Btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		farmer1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempFarmer = farmerTypes.get(0);
				selectedSkillLbl.setText("+" + tempFarmer.getCropBonus() + " Crop Growth");
				
			}
		});
		farmer1Btn.setBounds(20, 307, 113, 45);
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
		farm2Btn.setBounds(441, 307, 113, 45);
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
		farm4Btn.setBounds(441, 358, 113, 45);
		farmSetupWindow.getContentPane().add(farm4Btn);
		
		
		
		
		
		
	}
}
