package main;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	static Farmer player;
	static Farm playerFarm;
	static int daysRemaining;
	static int actions;
	static Store store;
	static boolean nextDay;
	
	public Game() { //Farmer farmer, Farm farm) {
//		player = farmer;
//		playerFarm = farm;
	}
	
	public Farmer getPlayer() {
		return player;
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
	
	
	public static void setFarmer(String name, int age) {
		Scanner in = new Scanner(System.in);
		int skill;
	    Farmer1 farmer1 = new Farmer1();
	    Farmer2 farmer2 = new Farmer2();
	    System.out.println("Select farmer skill number:\n");
		System.out.println("Skill 1: " + farmer1.getSkill());
		System.out.println("Skill 2: " + farmer2.getSkill());
		do {
			skill = in.nextInt();
			in.nextLine();
		} while ((skill != 1) && (skill != 2));
		if (skill == 1) {
			player = farmer1;
		} else {
			player = farmer2;
		}
		player.setAge(age);
		player.setName(name);		
	}
	
	public static void setFarm(String name) {
		Scanner in = new Scanner(System.in);
		int farmNum;
		List<Farm> farmTypes = new ArrayList<Farm>();
		farmTypes.add(new Farm1());
		farmTypes.add(new Farm2());
		farmTypes.add(new Farm3());
		farmTypes.add(new Farm4());
		System.out.println("Enter the farm number you would like to start with:\n");
		for (int i = 0; i < farmTypes.size(); i++) {
			System.out.println(i+1 + " : " + farmTypes.get(i).getType());
		}
		do {
			farmNum = in.nextInt();
			in.nextLine();
		} while ((farmNum > 4) || (farmNum < 1));
		playerFarm = farmTypes.get(farmNum-1);
		playerFarm.setName(name);
		playerFarm.setFarmer(player);		
	}
	
	
	public static Crop selectCrop(List<Crop> crops) {
		Scanner in = new Scanner(System.in);
		int i = 0;
		Crop selectedCrop = null;
		while(i < crops.size()) {
			System.out.println(i+1 + " : " + crops.get(i).getType());
			 i++;
		}
		System.out.println(i+1 + " : " + "Exit");
		int indexCrop;
		do {
		    indexCrop = in.nextInt() - 1;
		    in.nextLine();
		} while ((indexCrop < 0) || (indexCrop >= crops.size()));
		if (indexCrop != i) {
		selectedCrop = crops.get(indexCrop);
		}
		return selectedCrop;
	}
	
	public static int selectCropItem(List<CropItem> items) {
		Scanner in = new Scanner(System.in);
		int i = 0;	
		if (items.size() == 0) {
			System.out.println("You don't own any crop items.");
		}
		else {
			while (i < items.size()) {
				System.out.println(i+1 + " : " + items.get(i).getName());
				i++;
			}
		}
		System.out.println(i+1 + " : " + "Exit");
		int index;
		do {
		    index = in.nextInt() - 1;
		    in.nextLine();
		} while ((index > i) || (index < -1));
		
		if (index == i) {
			index = -2;
		}
		return index;
	}
	
	public static void tendToCrop() {
		Scanner in = new Scanner(System.in);
		List<Crop> crops = playerFarm.getCrops();
		List<CropItem> cropItems = playerFarm.getCropItems();
		if (crops.size() == 0) {
			System.out.println("You have no crops to tend to.");
		}
		else {
			System.out.println("Enter the crop number you would you like to tend to:\n");
			Crop selectedCrop = selectCrop(crops);		
			if (selectedCrop != null) {
				System.out.println("Enter 0 to water crop or select item to use from your inventory:\n");
				System.out.println("0 : Water");
				int indexItem = selectCropItem(cropItems);				
				if (indexItem != -2) {
					if (indexItem == -1) {
						selectedCrop.water();
						actions--;
					}
					else {
						CropItem selectedItem = cropItems.get(indexItem);
						selectedCrop.tendCrop(selectedItem);
						playerFarm.useItem(selectedItem);
						actions--; // reduce action count only if crop is tended to
					}
					System.out.println("Your " + selectedCrop.getType() + " crop is growing fast and healthy.");
				}
			}
		}
		System.out.println("\n");
	}
	
	public static void harvestCrop() {
		List<Crop> harvestCrops = new ArrayList<Crop>();
		for (Crop crop : playerFarm.getCrops()) {
			if (crop.readyToHarvest()) {
				harvestCrops.add(crop);
			}
		}
		if (harvestCrops.size() == 0) {
			System.out.println("You own no crops that are ready for harvest.");
		}
		else {
			System.out.println("Enter the crop number you would you like to harvest:\n");
			Crop selectedCrop = selectCrop(harvestCrops);
			if (selectedCrop != null) {
				double sellPrice = selectedCrop.harvest() * playerFarm.getCropMoneyBonus();
				System.out.println(selectedCrop.getType() + " crop harvested and sold for $" + sellPrice + " at the local market.");
				playerFarm.addMoney(sellPrice);
				playerFarm.removeCrop(selectedCrop);
				actions--;
			}
		}
		System.out.println("\n");
	}
	
	public static void playWithAnimals() {
		actions--;
		for (Animal animal : playerFarm.getAnimals()) {
			animal.play();
		}
		System.out.println("Your animals are filled with joy!\n");
	}
	
	
	public static void feedAnimals() {
		Scanner in = new Scanner(System.in);
		List<Animal> animals = playerFarm.getAnimals();
		List<AnimalItem> foodItems = playerFarm.getAnimalItems();
		if (animals.size() == 0) {
			System.out.println("You have no animals to feed.");
		}
		else if (foodItems.size() == 0) {
			System.out.println("You have no food items in your inventory.");
		}
		else {
			int foodIndex;
			int i = 0;
			System.out.println("Enter the item number for the food you would like to give to your animals:\n");
			while (i < foodItems.size()) {
				System.out.println(i+1 + " : " + foodItems.get(i).getName());
				i++;
			}
			System.out.println(i+1 + " : " + "Exit");
			do {
				foodIndex = in.nextInt() - 1;
				in.nextLine();
		    } while ((foodIndex > i) || (foodIndex < 0));
			
			if (foodIndex != i) {
				actions--;
				AnimalItem food = foodItems.get(foodIndex);
				for (int j = 0; j < animals.size(); j++) { // need to decide if food items feed all owned animals or up to a certain amount
					animals.get(j).feed(food);
				}
				System.out.println("Your animals are well fed and nourished.");
				playerFarm.useItem(food);
			}
	    }
		System.out.println("\n");
	}	
	
	public static String tendLand() {
		String message = "";
		actions--;
		playerFarm.tendLand();
		message += "Your farm is looking tidy and well maintained after tending to the land.";
		message += "Your animals are healthier and happier, and your crops will grow faster.\n";
		return message;
	}
	
	public static void moneyBonus() { // daily money bonus for animal happiness/health
		for (Animal animal : playerFarm.getAnimals()) {
			double bonus = ((animal.getHappiness() + animal.getHealth())* 0.10) * playerFarm.getAnimalMoneyBonus(); 
			playerFarm.addMoney(bonus);
		}
	}
	
	public static void animalHealthBonus() { 
		for (Animal animal : playerFarm.getAnimals()) {
			animal.dailyBonus(playerFarm.getAnimalHealthBonus());
		}
	}
	
	public static String animalLoss() {
		boolean death = false;
		String message = "";
		List<Animal> deadAnimals = new ArrayList<Animal>();
		for (Animal animal : playerFarm.getAnimals()) {
			String condition = animal.dailyLoss();
			if (condition != "") {
				message += ("A " + animal.getType() + " on your farm died overnight due to the following condition(s):\n");
				message += (condition + "\n");
				deadAnimals.add(animal);
				
				death = true;
			}	
		}
		if (death) { 
			for (Animal deadAnimal : deadAnimals) {
				playerFarm.deadAnimal(deadAnimal);
			}			
			message += ("\nFeed and play with your animals regularly to sustain their health and happiness.\n");
		}	
		return message;
	}
	
	public static void cropGrowth() {
		for (Crop crop : playerFarm.getCrops()) {
			crop.grow(playerFarm.getCropGrowthBonus());
		}
	}
	
	public String dailyChange() {
		String message = "on your farm died overnight due to the following condition(s):\\n\\nFeed and play with your animals regularly to sustain their health and happiness.\\n";
		moneyBonus();
		animalHealthBonus();
		if (daysRemaining > 0) {
			message += animalLoss();
		}
		cropGrowth();		
		return message;
	}
	
	
	public static double endGameAnimals() {
		double points = 0;
		for (Animal animal : playerFarm.getAnimals()) {
			points += (animal.getHappiness() + animal.getHealth()) * playerFarm.getAnimalMoneyBonus();
		}
		return points;
	}
	
	public static double endGameCrops() {
		double points = 0;
		for (Crop crop : playerFarm.getCrops()) {
			points += (crop.getAgeDays()/crop.getHarvestPeriod()) * crop.getSellPrice() * playerFarm.getCropMoneyBonus()*0.5; // scaled down points for partially grown crops
		}
		return points;
	}
	
	
	public static String endGame() {
		String message = "";
		double animalPoints = endGameAnimals();
		double cropPoints = endGameCrops();
		double moneyPoints = playerFarm.getMoney();
		double finalScore = animalPoints + cropPoints + moneyPoints;
		message += ("Congratulations, you finished the game!\n\n");
		message += ("You earned a total of:\n");
		message += (String.format("%.0f", animalPoints) + " points from your final animal health and happiness levels.\n");
		message += (String.format("%.0f", cropPoints) + " points from the crops you own that are still growing.\n");
		message += (String.format("%.0f", moneyPoints) + " points from the final total dollars in your farm's bank account.\n\n");
		message += ("Your final score is:\n");
		message += (String.format("%.0f", finalScore) + " points.");	
		return message;
	}
	
	public void mainGameLaunch() {
		MainGameWindow window = new MainGameWindow(this);
		
	}
	

	public static void main(String[] args) {
		Game game = new Game();
		StartGameWindow window = new StartGameWindow(game);
		
		
		
		
		
		
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("How many days would you like your game to last? Please Select 5-10");
			daysRemaining = in.nextInt();
			in.nextLine(); //consumes the \n char leftover from nextInt()
		}

		while ((daysRemaining < 5) | (daysRemaining > 10));		
		// TODO make sure names fit requirements
		
		System.out.println("What is your name?");
		String name = in.nextLine();
		System.out.println("How old are you?");
		int age = in.nextInt();
		in.nextLine();
		setFarmer(name, age);
		System.out.println("What would you like your farm to be called?");
		String farmName = in.nextLine();
		setFarm(name);
		store = new Store();
		
		while(daysRemaining > 0) {
			System.out.println("Good Morning! You have " + daysRemaining + " days remaining.");
			actions = 2;
			nextDay = false;
			
			while(!nextDay) {
				int action = 0;
				System.out.println("You have " + actions + " actions avalible. \n\n");
				do {
					System.out.println("What would you like to do? (please enter a number)");
					System.out.println("Free daily activities:");
					System.out.println("1) Check crop/animal status");
					System.out.println("2) Check farm bank account");
					System.out.println("3) Visit the store");
					System.out.println("4) Go to next day"); // Grouped free/counted actions together
					System.out.println("Counted daily actions:");
					System.out.println("5) Tend to a crop"); // added all actions here - can reduce action count within appropriate action methods
					System.out.println("6) Harvest a crop");
					System.out.println("7) Play with animals");
					System.out.println("8) Feed animals");
					System.out.println("9) Tend to farm land");
					action = in.nextInt();
					in.nextLine();
					
					if ((actions == 0) && ((action > 4) && (action < 10))) { // sets action out of range if a counted action is selected but no actions remaining
						System.out.println("Sorry, you have no daily actions remaining. Please select a free option.\n");
						action = 10;
					}
				}
				while ((action <= 0) || (action > 9)); // is it meant to be (action <= 0) rather than (action > 0)?
				if (action == 1) {
					System.out.println(playerFarm.checkFarmStatus());
				}
				else if (action == 2) {
					System.out.println("The farm currently has $" + playerFarm.getMoney() + " avalible");
				}
				else if (action == 3) {
					store.visitStore(playerFarm);
				}
				else if (action == 4) {
					if (actions > 0) {
						System.out.println("Are you sure? You still have " + actions + " remaining actions. (Please enter yes or no)");
						String answer = in.nextLine();
						if (answer.startsWith("y")) {
							nextDay = true;
						}
					} else {
						nextDay = true;
					}
				}
				else if (action == 5) {
					tendToCrop();
				}
				else if (action == 6) {
					harvestCrop();
				}
				else if (action == 7) {
					playWithAnimals();
				}
				else if (action == 8) {
					feedAnimals();
				}
				else if (action == 9) {
					System.out.println(tendLand());
				}
			}
			
			daysRemaining -= 1; // moved this out by one into "while (daysRemaining > 0)" loop
			//System.out.println(dailyChange()); // daily bonuses, growth
		}
		System.out.println(endGame()); 
	}
}
