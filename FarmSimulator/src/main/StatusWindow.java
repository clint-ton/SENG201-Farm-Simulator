package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class StatusWindow {

	private JFrame statusWindow;
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatusWindow window = new StatusWindow(new Game());
					window.statusWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StatusWindow(Game game) {
		this.game = game;
		initialize();
		statusWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		statusWindow = new JFrame();
		statusWindow.setTitle("Farm Status");
		statusWindow.setBounds(100, 100, 1000, 600);
		statusWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statusWindow.getContentPane().setLayout(null);
		
		JLabel statusLbl = new JLabel(game.getPlayerFarm().getName() + "Status");
		statusLbl.setHorizontalAlignment(SwingConstants.CENTER);
		statusLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		statusLbl.setBounds(10, 33, 966, 48);
		statusWindow.getContentPane().add(statusLbl);
		
		JLabel cropLbl = new JLabel("Crops");
		cropLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		cropLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropLbl.setBounds(64, 107, 399, 34);
		statusWindow.getContentPane().add(cropLbl);
		
		JLabel animalLbl = new JLabel("Animals");
		animalLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		animalLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		animalLbl.setBounds(528, 107, 399, 34);
		statusWindow.getContentPane().add(animalLbl);
		
		JLabel accBalanceLbl = new JLabel("New label");
		accBalanceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		accBalanceLbl.setBounds(508, 519, 392, 29);
		accBalanceLbl.setText("Account Balance: $" + game.getPlayerFarm().getMoney());
		statusWindow.getContentPane().add(accBalanceLbl);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainGameLaunch();
				statusWindow.dispose();
			}
		});
		backBtn.setBounds(24, 512, 131, 29);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		statusWindow.getContentPane().add(backBtn);
		
		DefaultListModel<Crop> cropListModel = new DefaultListModel<Crop>();
		cropListModel.addAll(game.getPlayerFarm().getCrops());
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane cropScroll = new JScrollPane(cropList);
		cropScroll.setBounds(64, 151, 399, 301);
		statusWindow.getContentPane().add(cropScroll);
		
		DefaultListModel<Animal> animalListModel = new DefaultListModel<Animal>();
		animalListModel.addAll(game.getPlayerFarm().getAnimals());
		JList<Animal> animalList = new JList<Animal>(animalListModel);
		animalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		animalList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane animalScroll = new JScrollPane(animalList);
		animalScroll.setBounds(528, 151, 399, 301);
		statusWindow.getContentPane().add(animalScroll);
		
		JLabel cropGrowthLbl = new JLabel("Crop Growth Bonus: " + game.getPlayerFarm().getCropGrowthBonus());
		cropGrowthLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropGrowthLbl.setBorder(null);
		cropGrowthLbl.setBounds(64, 450, 399, 34);
		statusWindow.getContentPane().add(cropGrowthLbl);
		
		JLabel animalHealthLbl = new JLabel("Animal Health Bonus: " + game.getPlayerFarm().getAnimalHealthBonus());
		animalHealthLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		animalHealthLbl.setBorder(null);
		animalHealthLbl.setBounds(528, 450, 399, 34);
		statusWindow.getContentPane().add(animalHealthLbl);
		
		JLabel cropMoneyLbl = new JLabel("Crop Money Multiplier: " + game.getPlayerFarm().getCropMoneyBonus());
		cropMoneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropMoneyLbl.setBorder(null);
		cropMoneyLbl.setBounds(64, 470, 399, 34);
		statusWindow.getContentPane().add(cropMoneyLbl);
		
		JLabel animalMoneyLbl = new JLabel("Animal Money Multiplier: " + game.getPlayerFarm().getAnimalMoneyBonus());
		animalMoneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		animalMoneyLbl.setBorder(null);
		animalMoneyLbl.setBounds(528, 470, 399, 34);
		statusWindow.getContentPane().add(animalMoneyLbl);
		
	}

}
