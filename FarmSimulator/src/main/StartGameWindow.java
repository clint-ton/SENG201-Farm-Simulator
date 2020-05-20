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

public class StartGameWindow {

	private JFrame frmFarmSetup;
	private JTextField playerNameText;
	private JTextField playerAgeText;
	private JTextField textField;
	private JLabel lblSelectNumberOf;
	
	private List<Farmer> farmerTypes = new ArrayList<Farmer>();
	private List<Farm> farmTypes = new ArrayList<Farm>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGameWindow window = new StartGameWindow();
					window.frmFarmSetup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartGameWindow() {
		createFarmerList();
		createFarmList();
		initialize();
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
		frmFarmSetup = new JFrame();
		frmFarmSetup.setTitle("Farm Setup");
		frmFarmSetup.setBounds(100, 100, 1000, 600);
		frmFarmSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSetup.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Farm Simulator!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(283, 22, 400, 48);
		frmFarmSetup.getContentPane().add(lblNewLabel);
		
		JLabel playerNameLabel = new JLabel("Enter your name:");
		playerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameLabel.setBounds(21, 118, 221, 29);
		frmFarmSetup.getContentPane().add(playerNameLabel);
		
		playerNameText = new JTextField();
		playerNameText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameText.setBounds(211, 118, 246, 29);
		frmFarmSetup.getContentPane().add(playerNameText);
		playerNameText.setColumns(10);
		
		JLabel playerAgeLabel = new JLabel("Enter your age:");
		playerAgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerAgeLabel.setBounds(21, 157, 221, 29);
		frmFarmSetup.getContentPane().add(playerAgeLabel);
		
		playerAgeText = new JTextField();
		playerAgeText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerAgeText.setColumns(10);
		playerAgeText.setBounds(211, 157, 246, 29);
		frmFarmSetup.getContentPane().add(playerAgeText);
		
		JLabel lblEnterYourFarm = new JLabel("Enter your farm name:");
		lblEnterYourFarm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterYourFarm.setBounds(21, 195, 221, 29);
		frmFarmSetup.getContentPane().add(lblEnterYourFarm);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(211, 196, 246, 29);
		frmFarmSetup.getContentPane().add(textField);
		
		lblSelectNumberOf = new JLabel("Select number of days:");
		lblSelectNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectNumberOf.setBounds(639, 118, 226, 29);
		frmFarmSetup.getContentPane().add(lblSelectNumberOf);
		
		JSlider daysRemainingSlider = new JSlider();
		daysRemainingSlider.setSnapToTicks(true);
		daysRemainingSlider.setValue(5);
		daysRemainingSlider.setPaintLabels(true);
		daysRemainingSlider.setPaintTicks(true);
		daysRemainingSlider.setMajorTickSpacing(1);
		daysRemainingSlider.setMinimum(5);
		daysRemainingSlider.setMaximum(10);
		daysRemainingSlider.setBounds(542, 160, 362, 22);
		frmFarmSetup.getContentPane().add(daysRemainingSlider);
		
		JLabel days6Label = new JLabel("6");
		days6Label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		days6Label.setBounds(615, 195, 10, 29);
		frmFarmSetup.getContentPane().add(days6Label);
		
		JLabel days7Label = new JLabel("7");
		days7Label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		days7Label.setBounds(684, 195, 10, 29);
		frmFarmSetup.getContentPane().add(days7Label);
		
		JLabel days8Label = new JLabel("8");
		days8Label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		days8Label.setBounds(754, 195, 10, 29);
		frmFarmSetup.getContentPane().add(days8Label);
		
		JLabel days9label = new JLabel("9");
		days9label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		days9label.setBounds(822, 195, 10, 29);
		frmFarmSetup.getContentPane().add(days9label);
		
		JLabel days10Label = new JLabel("10");
		days10Label.setHorizontalAlignment(SwingConstants.CENTER);
		days10Label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		days10Label.setBounds(882, 195, 26, 29);
		frmFarmSetup.getContentPane().add(days10Label);
		
		JLabel days5Label = new JLabel("5");
		days5Label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		days5Label.setBounds(545, 195, 10, 29);
		frmFarmSetup.getContentPane().add(days5Label);
		
		JLabel lblSelectYourFarmer = new JLabel("Select your farmer skill:");
		lblSelectYourFarmer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourFarmer.setBounds(43, 268, 221, 29);
		frmFarmSetup.getContentPane().add(lblSelectYourFarmer);
		
		JLabel lblSelectYourStarter = new JLabel("Select your starter farm:");
		lblSelectYourStarter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectYourStarter.setBounds(412, 268, 221, 29);
		frmFarmSetup.getContentPane().add(lblSelectYourStarter);
		
		DefaultListModel<Farmer> farmerSkillListModel = new DefaultListModel<Farmer>();

		
		JList<Farmer> farmerSkillList = new JList<Farmer>(farmerSkillListModel);
		farmerSkillList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		farmerSkillList.setBounds(43, 307, 326, 135);
		frmFarmSetup.getContentPane().add(farmerSkillList);
		
		JList<Farm> farmTypeList = new JList<Farm>();
		farmTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		farmTypeList.setBounds(412, 307, 533, 135);
		frmFarmSetup.getContentPane().add(farmTypeList);
		
		JButton startGameButton = new JButton("Start");
		startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		startGameButton.setBounds(845, 524, 131, 29);
		frmFarmSetup.getContentPane().add(startGameButton);
	}
}
