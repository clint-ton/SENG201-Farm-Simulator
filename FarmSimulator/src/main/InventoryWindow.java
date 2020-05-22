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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

public class InventoryWindow {

	private JFrame inventoryWindow;
	
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryWindow window = new InventoryWindow(new Game());
					window.inventoryWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InventoryWindow(Game game) {
		this.game = game;
		initialize();
		inventoryWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inventoryWindow = new JFrame();
		inventoryWindow.setTitle("Inventory");
		inventoryWindow.setBounds(100, 100, 1000, 600);
		inventoryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inventoryWindow.getContentPane().setLayout(null);
		
		JLabel inventoryLbl = new JLabel(game.getPlayer().getName() + "'s Inventory");
		inventoryLbl.setHorizontalAlignment(SwingConstants.CENTER);
		inventoryLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		inventoryLbl.setBounds(10, 33, 966, 48);
		inventoryWindow.getContentPane().add(inventoryLbl);
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.storeLaunch();
				inventoryWindow.dispose();
			}
		});
		backBtn.setBounds(24, 512, 131, 29);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inventoryWindow.getContentPane().add(backBtn);
		
		JLabel accBalanceLbl = new JLabel("New label");
		accBalanceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		accBalanceLbl.setBounds(376, 512, 392, 29);
		accBalanceLbl.setText("Account Balance: $" + game.getPlayerFarm().getMoney());
		inventoryWindow.getContentPane().add(accBalanceLbl);
		
		JLabel cropItemLbl = new JLabel("Crop Items");
		cropItemLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		cropItemLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropItemLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropItemLbl.setBounds(64, 107, 399, 34);
		inventoryWindow.getContentPane().add(cropItemLbl);
		
		JLabel animalItemsLbl = new JLabel("Animal Items");
		animalItemsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		animalItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalItemsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		animalItemsLbl.setBounds(529, 107, 398, 34);
		inventoryWindow.getContentPane().add(animalItemsLbl);
		
		DefaultListModel<Crop> cropListModel = new DefaultListModel<Crop>();
		cropListModel.addAll(game.getPlayerFarm().getCrops());
		
		DefaultListModel<CropItem> cropItemListModel = new DefaultListModel<CropItem>();
		cropItemListModel.addAll(game.getPlayerFarm().getCropItems());
		JList<CropItem> cropItemList = new JList<CropItem>(cropItemListModel);
		cropItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropItemList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane cropItemScroll = new JScrollPane(cropItemList);
		cropItemScroll.setBounds(64, 151, 399, 312);
		inventoryWindow.getContentPane().add(cropItemScroll);
		
		DefaultListModel<Animal> animalListModel = new DefaultListModel<Animal>();
		animalListModel.addAll(game.getPlayerFarm().getAnimals());
		
		DefaultListModel<AnimalItem> animalItemListModel = new DefaultListModel<AnimalItem>();
		animalItemListModel.addAll(game.getPlayerFarm().getAnimalItems());
		JList<AnimalItem> animalItemList = new JList<AnimalItem>(animalItemListModel);
		animalItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		animalItemList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane animalItemScroll = new JScrollPane(animalItemList);
		animalItemScroll.setBounds(528, 151, 399, 312);
		inventoryWindow.getContentPane().add(animalItemScroll);
		
		JButton instructionsBtn = new JButton("Help");
		instructionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Inventory holds the items you currently own." + Game.nln;
				message += "Crop items can be used to tend to crops." + Game.nln;
				message += "Animal items can be used to feed animals.";
				JOptionPane frame = new JOptionPane();
				JOptionPane.showMessageDialog(frame, message);	
			}
		});
		instructionsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsBtn.setBounds(892, 15, 75, 29);
		inventoryWindow.getContentPane().add(instructionsBtn);
	}

}
