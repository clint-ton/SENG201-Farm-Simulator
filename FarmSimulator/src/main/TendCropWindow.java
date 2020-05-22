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

public class TendCropWindow {

	private JFrame tendCropWindow;
	private Crop selectedCrop = null;
	private CropItem selectedItem = null;
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TendCropWindow window = new TendCropWindow(new Game());
					window.tendCropWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
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
		
		JLabel tendToCroplbl = new JLabel("Tend to a Crop");
		tendToCroplbl.setBounds(10, 33, 966, 48);
		tendToCroplbl.setHorizontalAlignment(SwingConstants.CENTER);
		tendToCroplbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		tendCropWindow.getContentPane().add(tendToCroplbl);
		
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
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLabel.setBounds(304, 512, 366, 29);
		tendCropWindow.getContentPane().add(errorLabel);
		
		
		JLabel selectedCropLbl = new JLabel("Selected Crop: ");
		selectedCropLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedCropLbl.setBounds(62, 442, 399, 29);
		tendCropWindow.getContentPane().add(selectedCropLbl);
		
		JLabel selectedItemLbl = new JLabel("Selected Item: ");
		selectedItemLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedItemLbl.setBounds(523, 442, 399, 29);
		tendCropWindow.getContentPane().add(selectedItemLbl);
		
		DefaultListModel<CropItem> cropItemListModel = new DefaultListModel<CropItem>();
		cropItemListModel.addAll(game.getPlayerFarm().getCropItems());
		JList<CropItem> cropItemList = new JList<CropItem>(cropItemListModel);
		cropItemList.setBackground(UIManager.getColor("Button.background"));
		cropItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane cropItemScroll = new JScrollPane(cropItemList);
		cropItemScroll.setBounds(523, 167, 399, 230);
		tendCropWindow.getContentPane().add(cropItemScroll);
		
		
		DefaultListModel<Crop> cropListModel = new DefaultListModel<Crop>();
		cropListModel.addAll(game.getPlayerFarm().getCrops());
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setBackground(UIManager.getColor("Button.background"));
		cropList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane cropScroll = new JScrollPane(cropList);
		cropScroll.setBounds(62, 167, 399, 230);
		tendCropWindow.getContentPane().add(cropScroll);
		
		JButton tendBtn = new JButton("Tend to Crop");
		tendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrop == null) {
					errorLabel.setText("No crop selected.");
				} else if (selectedItem == null) {
					errorLabel.setText("No item selected.");					
				} else {
					String message = game.tendToCrop(selectedItem, selectedCrop);
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);					
					game.mainGameLaunch();
					tendCropWindow.dispose();
				}
			}
		});
		tendBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendBtn.setBounds(789, 512, 172, 29);
		tendCropWindow.getContentPane().add(tendBtn);
		
		JButton selectCropBtn = new JButton("Select Crop");
		selectCropBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cropList.getSelectedIndex() != -1) {
					selectedCrop = cropList.getSelectedValue();
					selectedCropLbl.setText("Selected Crop: " + selectedCrop.getType());
				}
			}
		});
		selectCropBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectCropBtn.setBounds(184, 407, 145, 29);
		tendCropWindow.getContentPane().add(selectCropBtn);
		
		JButton selectItemBtn = new JButton("Select Item");
		selectItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cropItemList.getSelectedIndex() != -1) {
					selectedItem = cropItemList.getSelectedValue();
					selectedItemLbl.setText("Selected Item: " + selectedItem.getName());
				}
			}
		});
		selectItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectItemBtn.setBounds(650, 407, 145, 29);
		tendCropWindow.getContentPane().add(selectItemBtn);
		
		JButton waterBtn = new JButton("Water Crop");
		waterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrop == null) {
					errorLabel.setText("No crop selected.");
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
		waterBtn.setBounds(789, 481, 172, 29);
		tendCropWindow.getContentPane().add(waterBtn);
		
		JLabel cropsLbl = new JLabel(game.getPlayer().getName() + "'s Crops:");
		cropsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getCrops().size() == 0) {
			cropsLbl.setText("You have no Crops.");
		}
		cropsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropsLbl.setBounds(62, 125, 399, 32);
		tendCropWindow.getContentPane().add(cropsLbl);
		
		JLabel cropItemsLbl = new JLabel(game.getPlayer().getName() + "'s Crop Items:");
		cropItemsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getCropItems().size() == 0) {
			cropItemsLbl.setText("You have no Crop Items.");
		}
		cropItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropItemsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropItemsLbl.setBounds(523, 125, 399, 32);
		tendCropWindow.getContentPane().add(cropItemsLbl);
		
		
		
	}
}
