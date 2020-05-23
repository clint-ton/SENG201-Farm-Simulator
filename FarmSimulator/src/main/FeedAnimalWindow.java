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
import javax.swing.ListSelectionModel;

/**
 * Feed player animals GUI window
 * @author jke99, cjw237
 *
 */
public class FeedAnimalWindow {

	private JFrame feedAnimalWindow;
	
	private Game game;
	private List<Animal> selectedAnimals = new ArrayList<Animal>();
	private AnimalItem selectedFood = null;

	

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
		feedAnimalWindow.setTitle("Feed Animals");
		feedAnimalWindow.setBounds(100, 100, 1000, 600);
		feedAnimalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		feedAnimalWindow.getContentPane().setLayout(null);
		
		/**
		 * Header labels
		 */
		JLabel feedAnimalsLbl = new JLabel("Feed Animals");
		feedAnimalsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		feedAnimalsLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		feedAnimalsLbl.setBounds(10, 33, 966, 48);
		feedAnimalWindow.getContentPane().add(feedAnimalsLbl);
			
		JLabel animalsLbl = new JLabel(game.getPlayer().getName() + "'s Animals:");
		animalsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getAnimals().size() == 0) {
			animalsLbl.setText("You have no Animals.");
		}
		animalsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		animalsLbl.setBounds(62, 125, 399, 32);
		feedAnimalWindow.getContentPane().add(animalsLbl);
		
		JLabel foodLbl = new JLabel(game.getPlayer().getName() + "'s Food Items:");
		foodLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		if (game.getPlayerFarm().getAnimalItems().size() == 0) {
			foodLbl.setText("You have no Food Items.");
		}
		foodLbl.setHorizontalAlignment(SwingConstants.CENTER);
		foodLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		foodLbl.setBounds(523, 125, 399, 32);
		feedAnimalWindow.getContentPane().add(foodLbl);
		
		/**
		 * Displays Animals player selected to feed
		 */
		JLabel selectedAnimalsLbl = new JLabel("0 Animals selected.");
		selectedAnimalsLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedAnimalsLbl.setBounds(62, 442, 158, 29);
		feedAnimalWindow.getContentPane().add(selectedAnimalsLbl);
		
		/**
		 * Displays Food Item player selected to feed animals with
		 */
		JLabel selectedFoodLbl = new JLabel("Selected Food: ");
		selectedFoodLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectedFoodLbl.setBounds(523, 442, 399, 29);
		feedAnimalWindow.getContentPane().add(selectedFoodLbl);
		
		/**
		 * Explains animal selection
		 */
		JLabel ctrlshiftLbl = new JLabel("Ctrl+Click or Shift+Click to select up to 10 animals.");
		ctrlshiftLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ctrlshiftLbl.setBounds(62, 467, 399, 29);
		feedAnimalWindow.getContentPane().add(ctrlshiftLbl);
		
		/**
		 * Alerts player if no Animal or no Food Item selected when attempting to feed Animals
		 */
		JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLbl.setBounds(304, 512, 366, 29);
		feedAnimalWindow.getContentPane().add(errorLbl);
		
		
		/**
		 * List of Animals owned by player
		 * Contained in JScrollPane to allow scrolling if list exceeds text area
		 */
		DefaultListModel<Animal> animalListModel = new DefaultListModel<Animal>();
		animalListModel.addAll(game.getPlayerFarm().getAnimals());
		JList<Animal> animalList = new JList<Animal>(animalListModel);
		animalList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane animalsScroll = new JScrollPane(animalList);
		animalsScroll.setBounds(62, 168, 399, 230);
		feedAnimalWindow.getContentPane().add(animalsScroll);
		
		/**
		 * List of Food Items owned by player
		 * Contained in JScrollPane to allow scrolling if list exceeds text area
		 */
		DefaultListModel<AnimalItem> foodListModel = new DefaultListModel<AnimalItem>();
		foodListModel.addAll(game.getPlayerFarm().getAnimalItems());
		JList<AnimalItem> foodList = new JList<AnimalItem>(foodListModel);
		foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		foodList.setBackground(UIManager.getColor("Button.background"));
		JScrollPane foodScroll = new JScrollPane(foodList);
		foodScroll.setBounds(523, 167, 399, 230);
		feedAnimalWindow.getContentPane().add(foodScroll);
		
		
		/**
		 * Sets selectedAnimals variable to Animals selected in list
		 * Sets errorLbl text to to notify player if more than 10 animals selected
		 */
		JButton selectAnimalsBtn = new JButton("Select Animals");
		selectAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (animalList.getSelectedIndex() != -1) {
					if (animalList.getSelectedValuesList().size() > 10) {
						errorLbl.setText("Can only feed up to 10 animals.");
					} else {
						selectedAnimals = animalList.getSelectedValuesList();
						errorLbl.setText("");
					}
					selectedAnimalsLbl.setText(selectedAnimals.size() + " Animals selected.");
				}
			}
		});
		selectAnimalsBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectAnimalsBtn.setBounds(169, 408, 172, 29);
		feedAnimalWindow.getContentPane().add(selectAnimalsBtn);
		
		/**
		 * Sets selectedFood variable to Food Item selected in list
		 */
		JButton selectFoodBtn = new JButton("Select Food");
		selectFoodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (foodList.getSelectedIndex() != -1) {
					selectedFood = foodList.getSelectedValue();
					selectedFoodLbl.setText("Selected Food: " + selectedFood.getName());
					errorLbl.setText("");
				}
			}
		});
		selectFoodBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectFoodBtn.setBounds(650, 407, 145, 29);
		feedAnimalWindow.getContentPane().add(selectFoodBtn);
		
		/**
		 * Calls method in Game class to feed the Animals held in variable selectedAnimals
		 *  Sets errorLbl text to to notify player if no Animals or no Food selected
		 */
		JButton feedBtn = new JButton("Feed Animals");
		feedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedAnimals.size() == 0) {
					errorLbl.setText("No Animal selected.");
				} else if (selectedFood == null) {
					errorLbl.setText("No food selected.");					
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
		
		
		
		/**
		 * Back button returns player to main window
		 */
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
		
		
		/**
		 * Help button displays JOptionPane with instructions and descriptions for the current window
		 */
		JButton instructionsBtn = new JButton("Help");
		instructionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLbl.setText("");
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
