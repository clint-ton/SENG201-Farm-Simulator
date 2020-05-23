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

/**
 * Player inventory GUI window
 * @author jke99, cjw237
 *
 */

public class InventoryWindow {

	private JFrame inventoryWindow;
	
	/**
	 * Current state game variable
	 */
	private Game game;

	/**
	 * Create the application.
	 * @param game Game state passed to window
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
		
		/**
		 * Header label
		 */
		JLabel inventoryLbl = new JLabel(game.getPlayer().getName() + "'s Inventory");
		inventoryLbl.setHorizontalAlignment(SwingConstants.CENTER);
		inventoryLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		inventoryLbl.setBounds(10, 33, 966, 48);
		inventoryWindow.getContentPane().add(inventoryLbl);
		
		/**
		 * Player farm account balance
		 */
		JLabel accBalanceLbl = new JLabel("New label");
		accBalanceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		accBalanceLbl.setBounds(376, 512, 392, 29);
		accBalanceLbl.setText("Account Balance: $" + game.getPlayerFarm().getMoney());
		inventoryWindow.getContentPane().add(accBalanceLbl);
		
		/**
		 * Crop Items label with player name
		 * Alerts player if no Crop Items owned 
		 */
		JLabel cropItemLbl = new JLabel("Crop Items");
		cropItemLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		cropItemLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropItemLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropItemLbl.setBounds(64, 107, 399, 34);
		if (game.getPlayerFarm().getCropItems().size() == 0) {
			cropItemLbl.setText("You have no Crop Items");
		}
		inventoryWindow.getContentPane().add(cropItemLbl);
		
		/**
		 * Animal Items label with player name
		 * Alerts player if no Animal Items owned 
		 */
		JLabel animalItemsLbl = new JLabel("Animal Items");
		animalItemsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		animalItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalItemsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		animalItemsLbl.setBounds(529, 107, 398, 34);
		if (game.getPlayerFarm().getAnimalItems().size() == 0) {
			animalItemsLbl.setText("You have no Animal Items");
		}
		inventoryWindow.getContentPane().add(animalItemsLbl);
		
		
		/**
		 * List of Crop Items owned by player
		 * Contained in JScrollPane to allow scrolling if list exceeds text area
		 */
		DefaultListModel<CropItem> cropItemListModel = new DefaultListModel<CropItem>();
		cropItemListModel.addAll(game.getPlayerFarm().getCropItems());
		JList<CropItem> cropItemList = new JList<CropItem>(cropItemListModel);
		cropItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropItemList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane cropItemScroll = new JScrollPane(cropItemList);
		cropItemScroll.setBounds(64, 151, 399, 312);
		inventoryWindow.getContentPane().add(cropItemScroll);
		
		/**
		 * List of Food Items owned by player
		 * Contained in JScrollPane to allow scrolling if list exceeds text area
		 */
		DefaultListModel<AnimalItem> animalItemListModel = new DefaultListModel<AnimalItem>();
		animalItemListModel.addAll(game.getPlayerFarm().getAnimalItems());
		JList<AnimalItem> animalItemList = new JList<AnimalItem>(animalItemListModel);
		animalItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		animalItemList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane animalItemScroll = new JScrollPane(animalItemList);
		animalItemScroll.setBounds(528, 151, 399, 312);
		inventoryWindow.getContentPane().add(animalItemScroll);
		
		/**
		 * Button returns player to store window
		 */
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
		
		/**
		 * Help button displays JOptionPane with instructions and descriptions for the current window
		 */
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
