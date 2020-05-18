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
	
	public Game(Farmer farmer, Farm farm) {
		player = farmer;
		playerFarm = farm;
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
		int indexCrop = in.nextInt() - 1;
		in.nextLine();
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
		int index = in.nextInt() - 1;
		in.nextLine();
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
			System.out.println("Enter the crop number you would you like to tend to:");
			Crop selectedCrop = selectCrop(crops);		
			if (selectedCrop != null) {
				System.out.println("Enter 0 to water crop or select item to use from your inventory: ");
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
			System.out.println("Enter the crop number you would you like to harvest:");
			Crop selectedCrop = selectCrop(harvestCrops);
			if (selectedCrop != null) {
				double sellPrice = selectedCrop.harvest();
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
			System.out.println("Enter the item number for the food you would like to give to your animals:");
			int i = 0;
			while (i < foodItems.size()) {
				System.out.println(i+1 + " : " + foodItems.get(i).getName());
				i++;
			}
			System.out.println(i+1 + " : " + "Exit");
			int foodIndex = in.nextInt() - 1;
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
	
	public static void tendLand() {
		actions--;
		playerFarm.tendLand();
		System.out.println("Your farm is looking tidy and well maintained after tending to the land.");
		System.out.println("Your animals are healthier and happier, and your crops will grow faster.\n");		
	}
	
	public static void moneyBonus() {
		for (Animal animal : playerFarm.getAnimals()) {
			double bonus = ((animal.getHappiness() + animal.getHealth())* 0.10); // daily bonus for animal happiness/health
			playerFarm.addMoney(bonus);
		}
	}
	
	public static void animalFarmBonus() {
		for (Animal animal : playerFarm.getAnimals()) {
			animal.dailyBonus(playerFarm.getAnimalBonus());
		}
	}
	
	public static void animalLoss() {
		boolean death = false;
		for (Animal animal : playerFarm.getAnimals()) {
			String condition = animal.dailyLoss();
			if (condition != "") {
				System.out.println("A " + animal.getType() + " on your farm died overnight due to the following condition(s):");
				System.out.println(condition);
				playerFarm.deadAnimal(animal);
				death = true;
			}	
		}
		if (death) { 
			System.out.println("Feed and play with your animals regularly to sustain their health and happiness.\n");
		}		
	}
	
	public static void cropGrowth() {
		for (Crop crop : playerFarm.getCrops()) {
			crop.grow(playerFarm.getCropBonus());
		}
	}
	
	public static void dailyChange() {
		moneyBonus();
		animalFarmBonus();
		if (daysRemaining > 0) {
			animalLoss();
		}
		cropGrowth();		
	}
	

	public static void main(String[] args) {
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
		player = new Farmer(name, age, "Skill");
		System.out.println("What would you like your farm to be called?");
		String farmName = in.nextLine();
		playerFarm = new Farm(farmName, "Type", player);
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
						System.out.println("Sorry, you have no daily actions remaining. Please select a free option.");
						action = 10;
					}
				}
				while ((action > 0) & (action > 9)); // is it meant to be (action <= 0) rather than (action > 0)?
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
					tendLand();
				}
				
				// more actions
				
					
			}
			
			daysRemaining -= 1; // moved this out by one into "while (daysRemaining > 0)" loop
			dailyChange(); // daily bonuses, growth
		}
		
	}
}
