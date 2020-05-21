package main;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	private Farmer player;
	private Farm playerFarm;
	private int daysRemaining;
	private int actions = 2;
	private Store store;
	private boolean nextDay;
	private static String nln = System.lineSeparator(); 
	
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
	
	// called from setup window
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
	
	public String waterCrop(Crop selectedCrop) {
		selectedCrop.water();
		actions--;
		return ("Your " + selectedCrop.getType() + " crop is growing fast and healthy.");
	}
	
	public String tendToCrop(CropItem selectedItem, Crop selectedCrop) {
		selectedCrop.tendCrop(selectedItem);
		playerFarm.useItem(selectedItem);
		actions--; // reduce action count only if crop is tended to
	    return ("Your " + selectedCrop.getType() + " crop is growing fast and healthy.");
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
		return ("Your animals are filled with joy!" + nln);
	}
	
	
	public String feedAnimals(AnimalItem food, List<Animal> animals) {
		String message = "";
		for (int j = 0; j < animals.size(); j++) { // need to decide if food items feed all owned animals or up to a certain amount
			animals.get(j).feed(food);
		}
		System.out.println("Your animals are well fed and nourished." + nln);
		playerFarm.useItem(food);
		
		actions--;
		return message;
	}	
	
	public String tendLand() {
		String message = "";
		actions--;
		playerFarm.tendLand();
		message += "Your farm is looking tidy and well maintained after tending to the land." + nln;
		message += "Your animals are healthier and happier, and your crops will grow faster." + nln;
		return message;
	}
	
	public void moneyBonus() { // daily money bonus for animal happiness/health
		for (Animal animal : playerFarm.getAnimals()) {
			double bonus = ((animal.getHappiness() + animal.getHealth())* 0.10) * playerFarm.getAnimalMoneyBonus(); 
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
		List<Animal> deadAnimals = new ArrayList<Animal>();
		for (Animal animal : playerFarm.getAnimals()) {
			String condition = animal.dailyLoss();
			if (condition != "") {
				message += ("A " + animal.getType() + " on your farm died overnight due to the following condition(s):" + nln);
				message += (condition + "" + nln);
				deadAnimals.add(animal);
				death = true;
			}	
		}
		if (death) { 
			for (Animal deadAnimal : deadAnimals) {
				playerFarm.deadAnimal(deadAnimal);
			}			
			message += (nln + "Feed and play with your animals regularly to sustain their health and happiness." + nln);
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
	
	
	public String endGame() {
		String message = "";
		double animalPoints = endGameAnimals();
		double cropPoints = endGameCrops();
		double moneyPoints = playerFarm.getMoney();
		double finalScore = animalPoints + cropPoints + moneyPoints;
		message += ("Congratulations, you finished the game!" + nln + nln);
		message += ("You earned a total of:" + nln);
		message += (String.format("%.0f", animalPoints) + " points from your final animal health and happiness levels." + nln);
		message += (String.format("%.0f", cropPoints) + " points from the crops you own that are still growing." + nln + "");
		message += (String.format("%.0f", moneyPoints) + " points from the final total dollars in your farm's bank account." + nln + nln);
		message += ("Your final score is:" + nln);
		message += (String.format("%.0f", finalScore) + " points.");	
		return message;
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
	
	public static void main(String[] args) {     // for GUI application
		Game game = new Game();
		StartGameWindow window = new StartGameWindow(game);
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
//				System.out.println("You have " + game.actions + " actions avalible. " + nln + "" + nln + "");
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
//						System.out.println("Sorry, you have no daily actions remaining. Please select a free option." + nln + "");
//						action = 10;
//					}
//				}
//				while ((action <= 0) || (action > 9)); // is it meant to be (action <= 0) rather than (action > 0)?
//				if (action == 1) {
//					System.out.println(game.playerFarm.checkFarmStatus());
//				}
//				else if (action == 2) {
//					System.out.println("The farm currently has $" + game.playerFarm.getMoney() + " avalible");
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
//					System.out.println(game.playWithAnimals());
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
//		System.out.println(game.endGame()); 
//	}
//}
