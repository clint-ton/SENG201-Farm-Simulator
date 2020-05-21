package main;

import java.awt.Color;
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

public class HarvestCropWindow {

	private JFrame harvestWindow;
	
	private Game game;
	
	private Crop selectedCrop = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HarvestCropWindow window = new HarvestCropWindow(new Game());
					window.harvestWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HarvestCropWindow(Game game) {
		this.game = game;
		initialize();
		harvestWindow.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		harvestWindow = new JFrame();
		harvestWindow.setTitle("Harvest Crop");
		harvestWindow.setBounds(100, 100, 1000, 600);
		harvestWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		harvestWindow.getContentPane().setLayout(null);
		
		
		JLabel harvestCropBtn = new JLabel("Harvest a Crop");
		harvestCropBtn.setHorizontalAlignment(SwingConstants.CENTER);
		harvestCropBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
		harvestCropBtn.setBounds(10, 33, 966, 48);
		harvestWindow.getContentPane().add(harvestCropBtn);
		
		
		JLabel cropsLbl = new JLabel(game.getPlayer().getName() + "'s Animals:");
		if (game.getPlayerFarm().getCrops().size() == 0) {
			cropsLbl.setText("You have no Animals.");
		}
		cropsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropsLbl.setBounds(293, 125, 399, 32);
		harvestWindow.getContentPane().add(cropsLbl);
		if (game.getPlayerFarm().getCropItems().size() == 0) {
			cropsLbl.setText("You have no Crops.");
		}
		
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainGameLaunch();
				harvestWindow.dispose();
			}
		});
		backBtn.setBounds(24, 512, 131, 29);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		harvestWindow.getContentPane().add(backBtn);
		
		JLabel selectedCropLbl = new JLabel("Selected Crop: ");
		selectedCropLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedCropLbl.setBounds(293, 447, 399, 29);
		harvestWindow.getContentPane().add(selectedCropLbl);
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLabel.setBounds(304, 512, 366, 29);
		harvestWindow.getContentPane().add(errorLabel);
		
		
		DefaultListModel<Crop> cropListModel = new DefaultListModel<Crop>();
		cropListModel.addAll(game.getPlayerFarm().getCrops());
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane cropScroll = new JScrollPane(cropList);
		cropScroll.setBounds(293, 168, 399, 230);
		harvestWindow.getContentPane().add(cropScroll);
		
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
		selectCropBtn.setBounds(405, 408, 172, 29);
		harvestWindow.getContentPane().add(selectCropBtn);
		
		JButton harvestBtn = new JButton("Harvest");
		harvestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedCrop == null) {
					errorLabel.setText("No crop selected.");
				} else {
					String message = game.harvestCrop(selectedCrop);
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);
					game.mainGameLaunch();
					harvestWindow.dispose();
				}
			}
		});
		harvestBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		harvestBtn.setBounds(828, 512, 131, 29);
		harvestWindow.getContentPane().add(harvestBtn);
		
		
		
		
	}

}
