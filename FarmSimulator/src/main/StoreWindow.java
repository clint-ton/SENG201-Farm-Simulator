package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

/**
 * Farm store GUI window
 * @author USER
 *
 */

public class StoreWindow {

	private JFrame storeWindow;
	/**
	 * Current state game variable
	 */
	private Game game;
	/**
	 * Text field for player to enter quantity of item to purchase
	 */
	private JTextField quantityText;
	
	/**
	 * Product selected by player for purchase
	 */
	private Object selectedProduct;
	/**
	 * Price of product selected by player for purchase
	 */
	private double selectedPrice = 0;
	/**
	 * Quantity of selected product to purchase
	 */
	private int quantity;


	/**
	 * Create the application.
	 */
	public StoreWindow(Game game) {
		this.game = game;
		initialize();
		storeWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		storeWindow = new JFrame();
		storeWindow.setTitle("Store");
		storeWindow.setBounds(100, 100, 1000, 600);
		storeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeWindow.getContentPane().setLayout(null);
		
		
		/**
		 * Header labels
		 */
		JLabel storeLabel = new JLabel("");
		storeLabel.setBounds(10, 37, 976, 48);
		storeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		storeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		storeLabel.setText("Welcome to the Store!");
		storeWindow.getContentPane().add(storeLabel);
		
		JLabel accBalanceLbl = new JLabel("New label");
		accBalanceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		accBalanceLbl.setBounds(344, 513, 392, 29);
		accBalanceLbl.setText("Account Balance: $" + game.getPlayerFarm().getMoney());
		storeWindow.getContentPane().add(accBalanceLbl);
		
		JLabel quantityLbl = new JLabel("Enter quantity:");
		quantityLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quantityLbl.setBounds(621, 429, 124, 29);
		storeWindow.getContentPane().add(quantityLbl);
		
		JLabel availableProductsLbl = new JLabel("Available Products");
		availableProductsLbl.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		availableProductsLbl.setBackground(UIManager.getColor("Button.shadow"));
		availableProductsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		availableProductsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		availableProductsLbl.setBounds(24, 161, 938, 29);
		storeWindow.getContentPane().add(availableProductsLbl);
		
		JLabel attributesLbl = new JLabel("Attributes");
		attributesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		attributesLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		attributesLbl.setBackground(SystemColor.controlShadow);
		attributesLbl.setBounds(745, 205, 217, 29);
		storeWindow.getContentPane().add(attributesLbl);
		
		JLabel animalsLbl = new JLabel("Animals");
		animalsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		animalsLbl.setBounds(450, 200, 117, 38);
		storeWindow.getContentPane().add(animalsLbl);
		
		JLabel animalItemsLbl = new JLabel("Animal Items");
		animalItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalItemsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		animalItemsLbl.setBounds(579, 200, 146, 38);
		storeWindow.getContentPane().add(animalItemsLbl);
		
		JLabel cropsLbl = new JLabel("Crops");
		cropsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropsLbl.setBounds(24, 200, 248, 38);
		storeWindow.getContentPane().add(cropsLbl);
		
		JLabel cropItemsLbl = new JLabel("Crop Items");
		cropItemsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cropItemsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropItemsLbl.setBounds(305, 200, 117, 38);
		storeWindow.getContentPane().add(cropItemsLbl);
	    /**
	     * End or labels
	     */
		
		
		/**
		 * Separators
		 */
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(UIManager.getColor("Button.shadow"));
		separator.setForeground(UIManager.getColor("Button.shadow"));
		separator.setBounds(289, 245, 9, 159);
		storeWindow.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(SystemColor.controlShadow);
		separator_1.setBackground(UIManager.getColor("Button.shadow"));
		separator_1.setBounds(435, 245, 9, 159);
		storeWindow.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(SystemColor.controlShadow);
		separator_2.setBackground(UIManager.getColor("Button.shadow"));
		separator_2.setBounds(580, 245, 9, 159);
		storeWindow.getContentPane().add(separator_2);
		
		/**
		 * Displays purchase price of quantity of the selected product
		 */
		JLabel purchasePriceLbl = new JLabel("Purchase Price: $" + selectedPrice);
		purchasePriceLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		purchasePriceLbl.setBounds(42, 455, 392, 29);
		storeWindow.getContentPane().add(purchasePriceLbl);
		
		/**
		 * Displays the name of the product currently selected by the player
		 */
		JLabel selectedProductLbl = new JLabel("Selected Product:");
		selectedProductLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		selectedProductLbl.setBounds(42, 427, 392, 29);
		storeWindow.getContentPane().add(selectedProductLbl);
		
		
		/**
		 * Alerts player when insufficient funds available, no product selected, or invalid quantity entered
		 */
		JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		errorLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLbl.setBounds(655, 468, 307, 35);
		storeWindow.getContentPane().add(errorLbl);
		
		/**
		 * Displays attributes the product currently selected by the player
		 */
		JTextPane attributesPane = new JTextPane();
		attributesPane.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		attributesPane.setBackground(UIManager.getColor("Button.background"));
		attributesPane.setBounds(745, 240, 217, 164);
		storeWindow.getContentPane().add(attributesPane);
		
		
		
		
		
		
		
		/**
		 * Buttons for each product available for purchase
		 * When clicked, the attributesPane text is set to display the attributes of the specific product
		 * The selectedProduct variable is set to the Object of the product selected
		 * The selectedPrice variable is set to the price variable of the Object selectedProduct
		 * The quantity is automatically set to one
		 */
		
		/**
		 * Buttons for Crops
		 */
		JButton cabbageBtn = new JButton("Cabbage");
		cabbageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Cabbage cabbage = new Cabbage();
				selectedPrice = cabbage.getPurchasePrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(cabbage.storeString());
				selectedProduct = cabbage;
				selectedProductLbl.setText("Selected Product: Cabbage");
				errorLbl.setText("");
			}
		});
		cabbageBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cabbageBtn.setBounds(157, 356, 117, 48);
		storeWindow.getContentPane().add(cabbageBtn);
		
		JButton beansBtn = new JButton("Beans");
		beansBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Beans beans = new Beans();
				selectedPrice = beans.getPurchasePrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(beans.storeString());
				selectedProduct = beans;
				selectedProductLbl.setText("Selected Product: Beans");
				errorLbl.setText("");
			}
		});
		beansBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		beansBtn.setBounds(24, 356, 117, 48);
		storeWindow.getContentPane().add(beansBtn);
		
		JButton berriesBtn = new JButton("Berries");
		berriesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Berries berries = new Berries();
				selectedPrice = berries.getPurchasePrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(berries.storeString());
				selectedProduct = berries;
				selectedProductLbl.setText("Selected Product: Berries");
				errorLbl.setText("");
			}
		});
		berriesBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		berriesBtn.setBounds(157, 298, 117, 48);
		storeWindow.getContentPane().add(berriesBtn);
		
		JButton cornBtn = new JButton("Corn");
		cornBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Corn corn = new Corn();
				selectedPrice = corn.getPurchasePrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(corn.storeString());
				selectedProduct = corn;
				selectedProductLbl.setText("Selected Product: Corn");
				errorLbl.setText("");
			}
		});
		cornBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cornBtn.setBounds(157, 240, 117, 48);
		storeWindow.getContentPane().add(cornBtn);
		
		JButton riceBtn = new JButton("Rice");
		riceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Rice rice = new Rice();
				selectedPrice = rice.getPurchasePrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(rice.storeString());
				selectedProduct = rice;
				selectedProductLbl.setText("Selected Product: Rice");	
				errorLbl.setText("");
			}
		});
		riceBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		riceBtn.setBounds(24, 240, 117, 48);
		storeWindow.getContentPane().add(riceBtn);
		
		JButton wheatBtn = new JButton("Wheat");
		wheatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Wheat wheat = new Wheat();
				selectedPrice = wheat.getPurchasePrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(wheat.storeString());
				selectedProduct = wheat;
				selectedProductLbl.setText("Selected Product: Wheat");
				errorLbl.setText("");
			}
		});
		wheatBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		wheatBtn.setBounds(24, 298, 117, 48);
		storeWindow.getContentPane().add(wheatBtn);
		
		
		
		
		
		/**
		 * Buttons for Crop Items
		 */
		JButton fertiliserBtn = new JButton("Fertiliser");
		fertiliserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Fertiliser fertiliser = new Fertiliser();
				selectedPrice = fertiliser.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(fertiliser.storeString());
				selectedProduct = fertiliser;
				selectedProductLbl.setText("Selected Product: Fertiliser");
				errorLbl.setText("");
			}
		});
		fertiliserBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fertiliserBtn.setBounds(305, 240, 117, 48);
		storeWindow.getContentPane().add(fertiliserBtn);
		
		JButton nutrientBoostBtn = new JButton("Nutrient Boost");
		nutrientBoostBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				NutrientBoost nutrient = new NutrientBoost();
				selectedPrice = nutrient.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(nutrient.storeString());
				selectedProduct = nutrient;
				selectedProductLbl.setText("Selected Product: Nutrient Boost");
				errorLbl.setText("");
			}
		});
		nutrientBoostBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nutrientBoostBtn.setBounds(305, 298, 117, 48);
		storeWindow.getContentPane().add(nutrientBoostBtn);
		
		JButton weedSprayBtn = new JButton("Weed Spray");
		weedSprayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				WeedSpray weedSpray = new WeedSpray();
				selectedPrice = weedSpray.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(weedSpray.storeString());
				selectedProduct = weedSpray;
				selectedProductLbl.setText("Selected Product: Weed Spray");
				errorLbl.setText("");
			}
		});
		weedSprayBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		weedSprayBtn.setBounds(305, 356, 117, 48);
		storeWindow.getContentPane().add(weedSprayBtn);
		
		
		/**
		 * Buttons for Animal Items
		 */
		JButton growthHormoneBtn = new JButton("Growth Hormone");
		growthHormoneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				GrowthHormone growth = new GrowthHormone();
				selectedPrice = growth.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(growth.storeString());
				selectedProduct = growth;
				selectedProductLbl.setText("Selected Product: Growth Hormone");
				errorLbl.setText("");
			}
		});
		growthHormoneBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		growthHormoneBtn.setBounds(596, 240, 117, 48);
		storeWindow.getContentPane().add(growthHormoneBtn);
		
		JButton grassFeedBtn = new JButton("Grass Feed");
		grassFeedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				GrassFeed grass = new GrassFeed();
				selectedPrice = grass.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(grass.storeString());
				selectedProduct = grass;
				selectedProductLbl.setText("Selected Product: Grass Feed");
				errorLbl.setText("");
			}
		});
		grassFeedBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		grassFeedBtn.setBounds(596, 298, 117, 48);
		storeWindow.getContentPane().add(grassFeedBtn);
		
		JButton immunityBoostBtn = new JButton("Immunity Boost");
		immunityBoostBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				ImmuneBoost immune = new ImmuneBoost();
				selectedPrice = immune.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(immune.storeString());
				selectedProduct = immune;
				selectedProductLbl.setText("Selected Product: Immunity Boost");
				errorLbl.setText("");
			}
		});
		immunityBoostBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		immunityBoostBtn.setBounds(596, 356, 117, 48);
		storeWindow.getContentPane().add(immunityBoostBtn);
		
		
		/**
		 * Buttons for Animals
		 */
		JButton goatBtn = new JButton("Goat");
		goatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Goat goat = new Goat();
				selectedPrice = goat.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(goat.storeString());
				selectedProduct = goat;
				selectedProductLbl.setText("Selected Product: Goat");
				errorLbl.setText("");
			}
		});
		goatBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		goatBtn.setBounds(450, 356, 117, 48);
		storeWindow.getContentPane().add(goatBtn);
		
		JButton chickenBtn = new JButton("Chicken");
		chickenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Chicken chicken = new Chicken();
				selectedPrice = chicken.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(chicken.storeString());
				selectedProduct = chicken;
				selectedProductLbl.setText("Selected Product: Chicken");
				errorLbl.setText("");
			}
		});
		chickenBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chickenBtn.setBounds(450, 298, 117, 48);
		storeWindow.getContentPane().add(chickenBtn);
		
		JButton cowBtn = new JButton("Cow");
		cowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantityText.setText("1");
				quantity = 1;
				Cow cow = new Cow();
				selectedPrice = cow.getPrice();
				purchasePriceLbl.setText("Purchase Price: $" + selectedPrice);
				attributesPane.setText(cow.storeString());
				selectedProduct = cow;
				selectedProductLbl.setText("Selected Product: Cow");
				errorLbl.setText("");
			}
		});
		cowBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cowBtn.setBounds(450, 240, 117, 48);
		storeWindow.getContentPane().add(cowBtn);
		 /**
		  * End of product buttons
		  */
		
		
		
		
		
		/**
		 * Text field for player to enter product quantity
		 */
		quantityText = new JTextField();
		quantityText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityText.setBounds(745, 429, 76, 29);
		storeWindow.getContentPane().add(quantityText);
		quantityText.setColumns(10);
		
	
		/**
		 * Sets quantity variable to value entered in quantityText text field
		 * Sets errorLbl text to notify player if quantity entered is not an integer
		 */
		JButton priceBtn = new JButton("Get Price");
		priceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quantityStr = quantityText.getText();
				try {
					int quantityInt = Integer.parseInt(quantityStr); 
					purchasePriceLbl.setText("Purchase Price: $" + quantityInt * selectedPrice);
					quantity = quantityInt;
					errorLbl.setText("");
				}catch(NumberFormatException f) {
					errorLbl.setText("Enter valid quantity.");
				}
			}
		});
		priceBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceBtn.setBounds(831, 428, 139, 31);
		storeWindow.getContentPane().add(priceBtn);
		
		
		/**
		 * Opens InventoryWindow to view player inventory
		 */
		JButton viewIntBtn = new JButton("View Inventory");
		viewIntBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.inventoryLaunch();
				storeWindow.dispose();
			}
		});
		viewIntBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		viewIntBtn.setBounds(24, 116, 938, 35);
		storeWindow.getContentPane().add(viewIntBtn);
		
		
		/**
		 * Sets errorLbl text to notify player if no item selected or insufficient funds to make purchase
		 * Otherwise calls method in Game class to purchase specified quantity of the Object selectedProduct
		 */
		JButton purchaseBtn = new JButton("Purchase");
		purchaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedProduct == null) {
					errorLbl.setText("No item selected.");
				} else if ((selectedPrice * quantity) > game.getPlayerFarm().getMoney()) {
					errorLbl.setText("Insufficient funds.");
				} else {
					String message = game.purchaseProduct(selectedProduct, selectedPrice, quantity);
					JOptionPane frame = new JOptionPane();
					JOptionPane.showMessageDialog(frame, message);
					game.storeLaunch();
					storeWindow.dispose();
				}
			}
		});
		purchaseBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		purchaseBtn.setBounds(831, 513, 131, 29);
		storeWindow.getContentPane().add(purchaseBtn);
		
		
		/**
		 * Button returns player to main window
		 */
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainGameLaunch();
				storeWindow.dispose();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backBtn.setBounds(35, 513, 131, 29);
		storeWindow.getContentPane().add(backBtn);
		
		/**
		 * Help button displays JOptionPane with instructions and descriptions for the current window
		 */
		JButton instructionsBtn = new JButton("Help");
		instructionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLbl.setText("");
				String message = "Click View Inventory to view currently owned items." + Game.nln;
				message += "Select a product to view its attributes." + Game.nln;
				message += "Adjust the quantity and click Get Price to get total purchase price." + Game.nln;
				message += "Click Purchase to purchase the entered quantity of the selected product.";
				JOptionPane frame = new JOptionPane();
				JOptionPane.showMessageDialog(frame, message);	
			}
		});
		instructionsBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		instructionsBtn.setBounds(892, 15, 75, 29);
		storeWindow.getContentPane().add(instructionsBtn);
		
	}
}
