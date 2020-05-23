package main;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;


/**
 * Tend to player owned crops GUI window
 * @author jke99, cjw237
 *
 */

public class TendCropWindow {

	private JFrame tendCropWindow;
	
	/**
	 * Crop selected to tend to variable
	 */
	private Crop selectedCrop = null;
	/**
	 * Crop item selected to use on Crop variable
	 */
	private CropItem selectedItem = null;
	/**
	 * Current state game variable
	 */
	private Game game;

	/**
	 * Create the application.
	 * @param game Game state passed to window
	 */
	public TendCropWindow(Game game) {
		this.game = game;
		initialize();
		tendCropWindow.setVisible(true);	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tendCropWindow = new JFrame();
		tendCropWindow.setTitle("Tend to Crop");
		tendCropWindow.setBounds(100, 100, 1000, 600);
		tendCropWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tendCropWindow.getContentPane().setLayout(null);
		
		
		/**
		 * Header Label
		 */
		JLabel tendToCroplbl = new JLabel("Tend to a Crop");
		tendToCroplbl.setBounds(10, 33, 966, 48);
		tendToCroplbl.setHorizontalAlignment(SwingConstants.CENTER);
		tendToCroplbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		tendCropWindow.getContentPane().add(tendToCroplbl);
		
		/**
		 * Displays Crop player selects to tend to
		 */
		JLabel selectedCropLbl = new JLabel("Selected Crop: ");
		selectedCropLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedCropLbl.setBounds(62, 442, 399, 29);
		tendCropWindow.getContentPane().add(selectedCropLbl);
		
		/**
		 * Displays Crop Item player selects to use on Crop
		 */
		JLabel selectedItemLbl = new JLabel("Selected Item: ");
		selectedItemLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedItemLbl.setBounds(523, 442, 399, 29);
		tendCropWindow.getContentPane().add(selectedItemLbl);
		
		/**
		 * Crops label with player name
		 * Alerts player if no Crops owned 
		 */
		JLabel cropsLbl = new JLabel(game.getPlayer().getName() + "'s Crops:");
		cropsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getCrops().size() == 0) {
			cropsLbl.setText("You have no Crops.");
		}
		cropsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropsLbl.setBounds(62, 125, 399, 32);
		tendCropWindow.getContentPane().add(cropsLbl);
		
		/**
		 * Crop Items label with player name
		 * Alerts player if no Crop Items owned 
		 */
		JLabel cropItemsLbl = new JLabel(game.getPlayer().getName() + "'s Crop Items:");
		cropItemsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getCropItems().size() == 0) {
			cropItemsLbl.setText("You have no Crop Items.");
		}
		cropItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropItemsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropItemsLbl.setBounds(523, 125, 399, 32);
		tendCropWindow.getContentPane().add(cropItemsLbl);
		
		/**
		 * Alerts player if no Crop or no Crop Item selected when attempting to tend to Crop
		 */
		JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLbl.setBounds(304, 512, 366, 29);
		tendCropWindow.getContentPane().add(errorLbl);
		
		/**
		 * List of Crop Items owned by player
		 * Contained in JScrollPane to allow scrolling if list exceeds text area
		 */
		DefaultListModel<CropItem> cropItemListModel = new DefaultListModel<CropItem>();
		cropItemListModel.addAll(game.getPlayerFarm().getCropItems());
		JList<CropItem> cropItemList = new JList<CropItem>(cropItemListModel);
		cropItemList.setBackground(UIManager.getColor("Button.background"));
		cropItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane cropItemScroll = new JScrollPane(cropItemList);
		cropItemScroll.setBounds(523, 167, 399, 230);
		tendCropWindow.getContentPane().add(cropItemScroll);
		
		/**
		 * List of Crops owned by player
		 * Contained in JScrollPane to allow scrolling if list exceeds text area
		 */
		DefaultListModel<Crop> cropListModel = new DefaultListModel<Crop>();
		cropListModel.addAll(game.getPlayerFarm().getCrops());
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setBackground(UIManager.getColor("Button.background"));
		cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane cropScroll = new JScrollPane(cropList);
		cropScroll.setBounds(62, 167, 399, 230);
		tendCropWindow.getContentPane().add(cropScroll);
		
		
		/**
		 * Sets selectedCrop variable to Crop selected in list
		 */
		JButton selectCropBtn = new JButton("Select Crop");
		selectCropBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cropList.getSelectedIndex() != -1) {
					selectedCrop = cropList.getSelectedValue();
					selectedCropLbl.setText("Selected Crop: " + selectedCrop.getType());
					errorLbl.setText("");
				}
			}
		});
		selectCropBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectCropBtn.setBounds(184, 407, 145, 29);
		tendCropWindow.getContentPane().add(selectCropBtn);
		
		/**
		 * Sets selectedItem variable to Crop Item selected in list
		 */
		JButton selectItemBtn = new JButton("Select Item");
		selectItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cropItemList.getSelectedIndex() != -1) {
					selectedItem = cropItemList.getSelectedValue();
					selectedItemLbl.setText("Selected Item: " + selectedItem.getName());
					errorLbl.setText("");
				}
			}
		});
		selectItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectItemBtn.setBounds(650, 407, 145, 29);
		tendCropWindow.getContentPane().add(selectItemBtn);
		
		
		/**
		 * Calls method in Game class to water the Crop held in variable selectedCrop
		 * Sets errorLbl text to to notify player if no Crop selected
		 */
		JButton waterBtn = new JButton("Water");
		waterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrop == null) {
					errorLbl.setText("No crop selected.");
				} else {
					String message = game.waterCrop(selectedCrop);
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);					
					game.mainGameLaunch();
					tendCropWindow.dispose();
				}
			}
		});
		waterBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		waterBtn.setBounds(789, 478, 172, 29);
		tendCropWindow.getContentPane().add(waterBtn);
		
		/**
		 * Calls method in Game class to tend to the Crop held in variable selectedCrop using Item in variable selectedItem
		 * Sets errorLbl text to to notify player if no Crop selected
		 */
		JButton useItemBtn = new JButton("Use Item");
		useItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrop == null) {
					errorLbl.setText("No crop selected.");
				} else if (selectedItem == null) {
					errorLbl.setText("No item selected.");					
				} else {
					String message = game.tendToCrop(selectedItem, selectedCrop);
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);					
					game.mainGameLaunch();
					tendCropWindow.dispose();
				}
			}
		});
		useItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		useItemBtn.setBounds(789, 512, 172, 29);
		tendCropWindow.getContentPane().add(useItemBtn);
		
		
		/**
		 * Back button returns player to main window
		 */
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainGameLaunch();
				tendCropWindow.dispose();
			}
		});
		backBtn.setBounds(24, 512, 131, 29);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendCropWindow.getContentPane().add(backBtn);
		
		
		/**
		 * Help button displays JOptionPane with instructions and descriptions for the current window
		 */
		JButton instructionsBtn = new JButton("Help");
		instructionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLbl.setText("");
				String message = "Select a crop from the list you would like to tend to." + Game.nln;
				message += "Clicking Water will boost the growth of your crop without using an item." + Game.nln;
				message += "Select an item and click Use Item to use an item on a crop." + Game.nln;
				message += "Using an item on your crop will give a bigger growth boost.";
				JOptionPane frame = new JOptionPane();
				JOptionPane.showMessageDialog(frame, message);	
			}
		});
		instructionsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsBtn.setBounds(892, 15, 75, 29);
		tendCropWindow.getContentPane().add(instructionsBtn);
		
		
		
	}
}
