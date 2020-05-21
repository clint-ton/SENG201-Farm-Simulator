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

public class StartGameWindow {

	private JFrame farmSetupWindow;
	private JTextField playerNameText;
	private JTextField playerAgeText;
	private JTextField farmNameText;
	private JLabel lblSelectNumberOf;
	
	private Game game;
	
	private List<Farmer> farmerTypes = new ArrayList<Farmer>();
	private List<Farm> farmTypes = new ArrayList<Farm>();
	

	Farmer selectedFarmer;
	Farm selectedFarm;

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
		
		JLabel lblNewLabel = new JLabel("Welcome to Farm Simulator!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 36, 976, 48);
		farmSetupWindow.getContentPane().add(lblNewLabel);
		
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
		
		JLabel lblSelectYourFarmer = new JLabel("Select your farmer skill:");
		lblSelectYourFarmer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourFarmer.setBounds(21, 268, 221, 29);
		farmSetupWindow.getContentPane().add(lblSelectYourFarmer);
		
		JLabel lblSelectYourStarter = new JLabel("Select your starter farm:");
		lblSelectYourStarter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourStarter.setBounds(318, 268, 221, 29);
		farmSetupWindow.getContentPane().add(lblSelectYourStarter);
		
		JLabel selectedFarmerLabel = new JLabel("Your Farmer Skill: ");
		selectedFarmerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedFarmerLabel.setBounds(21, 470, 292, 36);
		farmSetupWindow.getContentPane().add(selectedFarmerLabel);
		
		JLabel selectedFarmLabel = new JLabel("Your Starter Farm: ");
		selectedFarmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedFarmLabel.setBounds(318, 470, 341, 36);
		farmSetupWindow.getContentPane().add(selectedFarmLabel);
		
		DefaultListModel<Farmer> farmerSkillListModel = new DefaultListModel<Farmer>();
		farmerSkillListModel.addAll(farmerTypes);		
		JList<Farmer> farmerSkillList = new JList<Farmer>(farmerSkillListModel);
		farmerSkillList.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		farmerSkillList.setBackground(UIManager.getColor("Button.background"));
		farmerSkillList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		farmerSkillList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		farmerSkillList.setBounds(21, 307, 278, 96);
		farmSetupWindow.getContentPane().add(farmerSkillList);
		
		DefaultListModel<Farm> farmTypeListModel = new DefaultListModel<Farm>();
		farmTypeListModel.addAll(farmTypes);
		JList<Farm> farmTypeList = new JList<Farm>(farmTypeListModel);
		farmTypeList.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		farmTypeList.setBackground(UIManager.getColor("Button.background"));
		farmTypeList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		farmTypeList.setBounds(318, 307, 658, 96);
		farmSetupWindow.getContentPane().add(farmTypeList);
		
		
		
		JButton selectFarmerButton = new JButton("Select Farmer Skill");
		selectFarmerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmerSkillList.getSelectedIndex() != -1) {
					selectedFarmer = farmerSkillList.getSelectedValue();
					selectedFarmerLabel.setText("Your Farmer Skill: " + selectedFarmer.getSkill());
				}
			}
		});
		selectFarmerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectFarmerButton.setBounds(21, 421, 181, 29);
		farmSetupWindow.getContentPane().add(selectFarmerButton);
		
		JButton btnSelectStarterFarm = new JButton("Select Starter Farm");
		btnSelectStarterFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (farmTypeList.getSelectedIndex() != -1) {
					selectedFarm = farmTypeList.getSelectedValue();
					selectedFarmLabel.setText("Your Starter Farm: " + selectedFarm.getType());
				}
			}
		});
		btnSelectStarterFarm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelectStarterFarm.setBounds(318, 421, 189, 29);
		farmSetupWindow.getContentPane().add(btnSelectStarterFarm);
		
		JLabel startErrorLabel = new JLabel("");
		startErrorLabel.setForeground(Color.RED);
		startErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startErrorLabel.setBounds(703, 470, 273, 36);
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
				} else if (selectedFarmerLabel.getText() == "Your Farmer Skill: ") {
					startErrorLabel.setText("Please select farmer skill.");
				} else if (selectedFarmLabel.getText() == "Your Starter Farm: ") {
					startErrorLabel.setText("Please select starter farm.");
				} else {
					startErrorLabel.setText("");
					int age = Integer.parseInt(ageText);
					game.setPlayer(selectedFarmer, playerNameText.getText(), age);
					game.setPlayerFarm(selectedFarm, farmNameText.getText());
					game.setDaysRemaining(daysRemainingSlider.getValue());
					game.mainGameLaunch();
					farmSetupWindow.dispose();
				}
				
				
				
				
				
			}
		});
		startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		startGameButton.setBounds(840, 516, 131, 29);
		farmSetupWindow.getContentPane().add(startGameButton);
		
		
		
		
	}
}
