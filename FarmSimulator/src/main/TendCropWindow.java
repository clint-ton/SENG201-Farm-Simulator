package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class TendCropWindow {

	private JFrame tendCropWindow;
	
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
		backBtn.setBounds(24, 512, 131, 29);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendCropWindow.getContentPane().add(backBtn);
		
		
		
		DefaultListModel<CropItem> cropItemListModel = new DefaultListModel<CropItem>();
		cropItemListModel.addAll(game.getPlayerFarm().getCropItems());
		JList<CropItem> cropItemList = new JList<CropItem>(cropItemListModel);
		cropItemList.setBounds(523, 177, 399, 271);
		tendCropWindow.getContentPane().add(cropItemList);
		
		
		DefaultListModel<Crop> cropListModel = new DefaultListModel<Crop>();
		cropItemListModel.addAll(game.getPlayerFarm().getCropItems());
		JList<Crop> cropList = new JList<Crop>(cropListModel);
		cropList.setBounds(62, 177, 399, 271);
		tendCropWindow.getContentPane().add(cropList);
		
		JButton tendBtn = new JButton("Tend to Crop");
		tendBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendBtn.setBounds(789, 512, 172, 29);
		tendCropWindow.getContentPane().add(tendBtn);
	}
}
