package main;
import java.util.List;
import java.util.Scanner;

import commandLine.Store;

import java.util.ArrayList;

public class Game {
	private Farmer player;
	private Farm playerFarm;
	private int daysRemaining;
	private int actions = 2;
	private Store store;

//	private boolean nextDay;
	public static String nln = System.lineSeparator();  // newline character for specific machine
	
	public Game() { //Farmer farmer, Farm farm) {
//		player = farmer;
//		playerFarm = farm;
	}
	
	public Farmer getPlayer() {
		return player;
	}
	
	public int getActions() {
		return actions;
	}
	
	public Farm getPlayerFarm() {
		return playerFarm;
	}
	
	public int getDaysRemaining() {
		return daysRemaining;
	}
	
	public Store getStore() {
		return store;
	}
	
	// called from setup window
	/**
	 * Creates the player, called from setup window
	 * @param farmer
	 * @param name
	 * @param age
	 */
	public void setPlayer(Farmer farmer, String name, int age) {
		player = farmer;
		player.setAge(age);
		player.setName(name);
	}
	
	public void setPlayerFarm(Farm farm, String name) {
		playerFarm = farm;
		playerFarm.setName(name);
		playerFarm.setFarmer(player);
	}
	
	public void setDaysRemaining(int days) {
		daysRemaining = days;
	}
	
	public void setStore() {
		store = new Store();
	}
	
	public String waterCrop(Crop selectedCrop) {
		selectedCrop.water();
		actions--;
		return ("You watered your " + selectedCrop.getType() + " crop." + nln + "It is growing fast and healthy.");
	}
	
	public String purchaseCrops(Crop crop, int quantity) {
		String type = "";
		if (crop instanceof Cabbage) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Cabbage());
				type = crop.getType();
			}
		} else if (crop instanceof Berries) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Berries());
				type = crop.getType();
			}
		} else if (crop instanceof Corn) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Corn());
				type = crop.getType();
			}
		} else if (crop instanceof Rice) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Rice());
				type = crop.getType();
			}
		} else if (crop instanceof Wheat) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Wheat());
				type = crop.getType();
			}
		} else {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Beans());
				type = crop.getType();
			}
		}
		return type;
	}
	
	public String purchaseAnimals(Animal animal, int quantity) {
		String type = "";
		if (animal instanceof Cow) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseAnimal(new Cow());
				type = animal.getType();
			}
		} else if (animal instanceof Chicken) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseAnimal(new Cow());
				type = animal.getType();
			}
		} else {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseAnimal(new Goat());
				type = animal.getType();
			}
		}
		return type;
	}
	
	public String purchaseItems(Item item, int quantity) {
		String type = "";
		if (item instanceof Fertiliser) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new Fertiliser());
				type = item.getName();
			}
		} else if (item instanceof WeedSpray) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new WeedSpray());
				type = item.getName();
			}
		} else if (item instanceof NutrientBoost) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new NutrientBoost());
				type = item.getName();
			}
		} else if (item instanceof GrowthHormone) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new GrowthHormone());
				type = item.getName();
			}
		} else if (item instanceof GrassFeed) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new GrassFeed());
				type = item.getName();
			}
		} else {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new ImmuneBoost());
				type = item.getName();
			}
		}
		return type;
	}
	
	public String purchaseProduct(Object product, double price, int quantity) {
		String type;
		if (product instanceof Crop) {
			type = purchaseCrops((Crop) product, quantity);
		} else if (product instanceof Animal) {
			type = purchaseAnimals((Animal) product, quantity);
		} else {
			type = purchaseItems((Item) product, quantity);
		}
		return ("Success - " + quantity + " " + type + " purchased for $" + price*quantity);
	}
	
	
	
	public String tendToCrop(CropItem selectedItem, Crop selectedCrop) {
		selectedCrop.tendCrop(selectedItem);
		playerFarm.useItem(selectedItem);
		actions--; // reduce action count only if crop is tended to
	    return ("You used " + selectedItem.getName() + " on your " + selectedCrop.getType() + " crop." + nln + "It is growing fast and healthy.");
	}
	
	public String harvestCrop(Crop selectedCrop) {
		String message = "";
		double sellPrice = selectedCrop.harvest() * playerFarm.getCropMoneyBonus();
		message += (selectedCrop.getType() + " crop harvested and sold for $" + sellPrice + " at the local market.");
		playerFarm.addMoney(sellPrice);
		playerFarm.removeCrop(selectedCrop);
		actions--;
		return message;
	}
	
	public String playWithAnimals() {
		actions--;
		for (Animal animal : playerFarm.getAnimals()) {
			animal.play();
		}
		return ("Your animals are filled with joy!" + nln + "Animal Happiness = +20 Points");
	}
	
	
	public String feedAnimals(AnimalItem food, List<Animal> animals) {
		String message = "";
		for (int j = 0; j < animals.size(); j++) { // need to decide if food items feed all owned animals or up to a certain amount
			animals.get(j).feed(food);
		}
		message += ("You fed " + animals.size() + " animals with " + food.getName() + "." + nln + "They are well fed and nourished." + nln);
		playerFarm.useItem(food);
		
		actions--;
		return message;
	}	
	
	public String tendLand() {
		String message = "";
		actions--;
		playerFarm.tendLand();
		message += "Your farm is looking tidy and well maintained after tending to the land." + nln;
		message += "Crop Growth Bonus: +0.25 Days" + nln;
		message += "Animal Health Bonus: +2 Points" + nln;
		message += "Your animals will be healthier and happier, and your crops will grow faster." + nln;
		return message;
	}
	
	public void moneyBonus() { // daily money bonus for animal happiness/health
		for (Animal animal : playerFarm.getAnimals()) {
			double bonus = ((animal.getIncome()) * playerFarm.getAnimalMoneyBonus()); 
			playerFarm.addMoney(bonus);
		}
	}
	
	public void animalHealthBonus() { 
		for (Animal animal : playerFarm.getAnimals()) {
			animal.dailyBonus(playerFarm.getAnimalHealthBonus());
		}
	}
	
	public String animalLoss() {
		boolean death = false;
		String message = "";
		int count = 0;
		List<Animal> deadAnimals = new ArrayList<Animal>();
		for (int i = 0; i < playerFarm.getAnimals().size(); i++) {
			boolean deadAnimal = playerFarm.getAnimals().get(i).dailyLoss();
			if (deadAnimal) {
				count+=1;
				death = true;
				deadAnimals.add(playerFarm.getAnimals().get(i));
			}	
		}
		if (death) { 
			for (Animal deadAnimal : deadAnimals) {
				playerFarm.deadAnimal(deadAnimal);
			}
			message = (count + " of your animals died overnight due to happiness and/or health levels dropping below 0." + nln);
			message += ("Animals need nourishment and joy to survive on your farm." + nln);
			message += ("Feed and play with your animals regularly to sustain their health and happiness." + nln);
		}	
		return message;
	}
	
	public void cropGrowth() {
		for (Crop crop : playerFarm.getCrops()) {
			crop.grow(playerFarm.getCropGrowthBonus());
		}
	}
	
	public String dailyChange() {
		daysRemaining -= 1;
		actions = 2;
		String message = "";
		moneyBonus();
		animalHealthBonus();
		if (daysRemaining > 0) {
			message = animalLoss();
		}
		cropGrowth();		
		return message;
	}

	public double endGameAnimals() {
		double points = 0;
		for (Animal animal : playerFarm.getAnimals()) {
			points += (animal.getHappiness() + animal.getHealth()) * playerFarm.getAnimalMoneyBonus();
		}
		return points;
	}
	
	public double endGameCrops() {
		double points = 0;
		for (Crop crop : playerFarm.getCrops()) {
			points += (crop.getAgeDays()/crop.getHarvestPeriod()) * crop.getSellPrice() * playerFarm.getCropMoneyBonus()*0.5; // scaled down points for partially grown crops
		}
		return points;
	}
	
	public double endGameTotal() {
		return (endGameAnimals() + endGameCrops() + playerFarm.getMoney());
	}
	
	public String endGameMessage() {
		String message = "";
		double animalPoints = endGameAnimals();
		double cropPoints = endGameCrops();
		double moneyPoints = playerFarm.getMoney();
		message += (String.format("%.0f", animalPoints) + " points from your final animal health and happiness levels." + nln);
		message += (String.format("%.0f", cropPoints) + " points from the crops you own that are still growing." + nln + "");
		message += (String.format("%.0f", moneyPoints) + " points from the final total dollars in your farm's bank account." + nln + nln);
		return message;
	}
	
	
	public void startGameLaunch() {
		StartGameWindow window = new StartGameWindow(this);
	}
	
	public void mainGameLaunch() {
		MainGameWindow window = new MainGameWindow(this);
		
	}
	
	public void tendCropLaunch() {
		TendCropWindow window = new TendCropWindow(this);
	}
	
	public void feedAnimalLaunch() {
		FeedAnimalWindow window = new FeedAnimalWindow(this);
	}
	
	public void harvestCropLaunch() {
		HarvestCropWindow window = new HarvestCropWindow(this);
	}
	
	public void storeLaunch() {
		StoreWindow window = new StoreWindow(this);
	}
	
	public void inventoryLaunch() {
		InventoryWindow window = new InventoryWindow(this);
	}
	
	public void statusLaunch() {
		StatusWindow window = new StatusWindow(this);
	}
	
	public void endGameLaunch() {
		EndGameWindow window = new EndGameWindow(this);
	}
	
	public void restartGame() {
		Game game = new Game();
		game.startGameLaunch();
	}
	
	public static void main(String[] args) {     // for GUI application
		Game game = new Game();
		game.startGameLaunch();
	}
}
	

//	public static void main(String[] args) {     // for command line application
//		Game game = new Game();
//		UserInput selector = new UserInput(game);
//		Scanner in = new Scanner(System.in);
//		do {
//			System.out.println("How many days would you like your game to last? Please Select 5-10");
//			game.daysRemaining = in.nextInt();
//			in.nextLine(); //consumes the \n char leftover from nextInt()
//		}
//
//		while ((game.daysRemaining < 5) | (game.daysRemaining > 10));		
//		// TODO make sure names fit requirements
//		System.out.println("What is your name?");
//		String name = in.nextLine();
//		System.out.println("How old are you?");
//		int age = in.nextInt();
//		in.nextLine();
//		Farmer farmerType = selector.selectFarmer();
//		game.setPlayer(farmerType, name, age);
//		System.out.println("What would you like your farm to be called?");
//		String farmName = in.nextLine();
//		Farm farmType = selector.selectFarm();
//		game.setPlayerFarm(farmType, farmName);
//		game.store = new Store();
//		
//		while(game.daysRemaining > 0) {
//			System.out.println("Good Morning! You have " + game.daysRemaining + " days remaining.");
//			game.actions = 2;
//			game.nextDay = false;
//			
//			while(!game.nextDay) {
//				int action = 0;
//				System.out.println("You have " + game.actions + " actions available. " + Game.nln + "" + Game.nln + "");
//				do {
//					System.out.println("What would you like to do? (please enter a number)");
//					System.out.println("Free daily activities:");
//					System.out.println("1) Check crop/animal status");
//					System.out.println("2) Check farm bank account");
//					System.out.println("3) Visit the store");
//					System.out.println("4) Go to next day"); // Grouped free/counted actions together
//					System.out.println("Counted daily actions:");
//					System.out.println("5) Tend to a crop"); // added all actions here - can reduce action count within appropriate action methods
//					System.out.println("6) Harvest a crop");
//					System.out.println("7) Play with animals");
//					System.out.println("8) Feed animals");
//					System.out.println("9) Tend to farm land");
//					action = in.nextInt();
//					in.nextLine();
//					
//					if ((game.actions == 0) && ((action > 4) && (action < 10))) { // sets action out of range if a counted action is selected but no actions remaining
//						System.out.println("Sorry, you have no daily actions remaining. Please select a free option." + Game.nln + "");
//						action = 10;
//					}
//				}
//				while ((action <= 0) || (action > 9)); // is it meant to be (action <= 0) rather than (action > 0)?
//				if (action == 1) {
//					System.out.println(game.playerFarm.checkFarmStatus());
//				}
//				else if (action == 2) {
//					System.out.println("The farm currently has $" + game.playerFarm.getMoney() + " available");
//				}
//				else if (action == 3) {
//					game.store.visitStore(game.playerFarm);
//				}
//				else if (action == 4) {
//					if (game.actions > 0) {
//						System.out.println("Are you sure? You still have " + game.actions + " remaining actions. (Please enter yes or no)");
//						String answer = in.nextLine();
//						if (answer.startsWith("y")) {
//							game.nextDay = true;
//						}
//					} else {
//						game.nextDay = true;
//					}
//				}
//				else if (action == 5) {
//					Crop selectedCrop = selector.selectTendCrop(game.getPlayerFarm().getCrops());
//					if (selectedCrop != null) {
//						int itemIndex = selector.selectCropItem(game.getPlayerFarm().getCropItems());
//						if (itemIndex == -1) {
//							System.out.println(game.waterCrop(selectedCrop));
//						} else if (itemIndex != -2) {
//							CropItem selectedItem = game.getPlayerFarm().getCropItems().get(itemIndex);
//						    game.tendToCrop(selectedItem, selectedCrop);
//						}
//				    }
//				}
//				else if (action == 6) {
//					Crop selectedCrop = selector.selectHarvestCrop();
//					if (selectedCrop != null) {
//					    System.out.println(game.harvestCrop(selectedCrop));
//					}
//				}
//				else if (action == 7) {
//					if (game.getAnimals().size() == 0) {
//						System.out.println("You have no animals to play with.");
//					} else {
//						System.out.println(game.playWithAnimals());
//					}
// 
//				}
//				else if (action == 8) {
//					AnimalItem selectedFood = selector.selectAnimalItem();
//					if (selectedFood != null) {
//						System.out.println(game.feedAnimals(selectedFood,  game.getPlayerFarm().getAnimals()));
//					}
//				}
//				else if (action == 9) {
//					System.out.println(game.tendLand());
//				}
//			}
//			
//			System.out.println(game.dailyChange()); // daily bonuses, growth, reduce day count
//		}
//      System.out.println("Congratulations, you finished the game!" + Game.nln + Game.nln);
//      System.out.println("You score a total of: " + Game.nln);
//      System.out.println(game.endGame());
//        System.out.println("Your final score is: ");
//      System.out.println(String.format("%.0f", endGameTotal()) + " points.");
//	}
//}
