package main;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;

public class FeedAnimalWindow {

	private JFrame feedAnimalWindow;
	
	private Game game;
	private List<Animal> selectedAnimals = new ArrayList<Animal>();
	private AnimalItem selectedFood = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedAnimalWindow window = new FeedAnimalWindow(new Game());
					window.feedAnimalWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FeedAnimalWindow(Game game) {
		this.game = game;
		initialize();
		feedAnimalWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		feedAnimalWindow = new JFrame();
		feedAnimalWindow.setBounds(100, 100, 1000, 600);
		feedAnimalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		feedAnimalWindow.getContentPane().setLayout(null);
		
		JLabel lblFeedAnimals = new JLabel("Feed Animals");
		lblFeedAnimals.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeedAnimals.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblFeedAnimals.setBounds(10, 33, 966, 48);
		feedAnimalWindow.getContentPane().add(lblFeedAnimals);
		
		
		JLabel animalsLbl = new JLabel(game.getPlayer().getName() + "'s Animals:");
		animalsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getCrops().size() == 0) {
			animalsLbl.setText("You have no Animals.");
		}
		animalsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		animalsLbl.setBounds(62, 125, 399, 32);
		feedAnimalWindow.getContentPane().add(animalsLbl);
		
		JLabel foodLbl = new JLabel(game.getPlayer().getName() + "'s Food Items:");
		foodLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getCropItems().size() == 0) {
			foodLbl.setText("You have no Food Items.");
		}
		foodLbl.setHorizontalAlignment(SwingConstants.CENTER);
		foodLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		foodLbl.setBounds(523, 125, 399, 32);
		feedAnimalWindow.getContentPane().add(foodLbl);
		
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainGameLaunch();
				feedAnimalWindow.dispose();
			}
		});
		backBtn.setBounds(24, 512, 131, 29);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedAnimalWindow.getContentPane().add(backBtn);
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLabel.setBounds(304, 512, 366, 29);
		feedAnimalWindow.getContentPane().add(errorLabel);
		
		JLabel selectedAnimalsLbl = new JLabel("0 Animals selected.");
		selectedAnimalsLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedAnimalsLbl.setBounds(62, 442, 158, 29);
		feedAnimalWindow.getContentPane().add(selectedAnimalsLbl);
		
		JLabel selectedFoodLbl = new JLabel("Selected Food: ");
		selectedFoodLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedFoodLbl.setBounds(523, 442, 399, 29);
		feedAnimalWindow.getContentPane().add(selectedFoodLbl);
		
		
		DefaultListModel<Animal> animalListModel = new DefaultListModel<Animal>();
		animalListModel.addAll(game.getPlayerFarm().getAnimals());
		JList<Animal> animalList = new JList<Animal>(animalListModel);
		animalList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane animalsScroll = new JScrollPane(animalList);
		animalsScroll.setBounds(62, 168, 399, 230);
		feedAnimalWindow.getContentPane().add(animalsScroll);
		
		
		DefaultListModel<AnimalItem> foodListModel = new DefaultListModel<AnimalItem>();
		foodListModel.addAll(game.getPlayerFarm().getAnimalItems());
		JList<AnimalItem> foodList = new JList<AnimalItem>(foodListModel);
		foodList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane foodScroll = new JScrollPane(foodList);
		foodScroll.setBounds(523, 167, 399, 230);
		feedAnimalWindow.getContentPane().add(foodScroll);
		
		

		JButton selectAnimalsBtn = new JButton("Select Animals");
		selectAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (animalList.getSelectedIndex() != -1) {
					if (animalList.getSelectedValuesList().size() > 10) {
						errorLabel.setText("Can only feed up to 10 animals.");
					} else {
						selectedAnimals = animalList.getSelectedValuesList();
					}
					selectedAnimalsLbl.setText(selectedAnimals.size() + " Animals selected.");
				}
			}
		});
		selectAnimalsBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectAnimalsBtn.setBounds(169, 408, 172, 29);
		feedAnimalWindow.getContentPane().add(selectAnimalsBtn);
		
		JButton selectFoodBtn = new JButton("Select Food");
		selectFoodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (foodList.getSelectedIndex() != -1) {
					selectedFood = foodList.getSelectedValue();
					selectedFoodLbl.setText("Selected Food: " + selectedFood.getName());
				}
			}
		});
		selectFoodBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectFoodBtn.setBounds(650, 407, 145, 29);
		feedAnimalWindow.getContentPane().add(selectFoodBtn);
		
		
		JButton feedBtn = new JButton("Feed Animals");
		feedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAnimals.size() == 0) {
					errorLabel.setText("No Animal selected.");
				} else if (selectedFood == null) {
					errorLabel.setText("No food selected.");					
				} else {
					String message = game.feedAnimals(selectedFood, selectedAnimals);
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);					
					game.mainGameLaunch();
					feedAnimalWindow.dispose();
				}
			}
		});
		feedBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedBtn.setBounds(789, 512, 172, 29);
		feedAnimalWindow.getContentPane().add(feedBtn);
		
		JLabel ctrlshiftLbl = new JLabel("Ctrl+Click or Shift+Click to select up to 10 animals.");
		ctrlshiftLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ctrlshiftLbl.setBounds(62, 467, 399, 29);
		feedAnimalWindow.getContentPane().add(ctrlshiftLbl);
		
		JButton instructionsBtn = new JButton("Help");
		instructionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Select up to 10 animals you would like to feed." + Game.nln;
				message += "Use Ctrl+Click or Shift+Click to select multiple animals." + Game.nln;
				message += "Select an item and click Feed Animals to feed selected animals." + Game.nln;
				message += "Fed animals receive a health boost from the item.";
				JOptionPane frame = new JOptionPane();
				JOptionPane.showMessageDialog(frame, message);	
			}
		});
		instructionsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsBtn.setBounds(892, 15, 75, 29);
		feedAnimalWindow.getContentPane().add(instructionsBtn);
		

		
	
	}
}
