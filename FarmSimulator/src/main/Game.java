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
		for (int i = 0; i < crops.size(); i++) {
			System.out.println(i+1 + " : " + crops.get(i).getType());
		}
		int indexCrop = in.nextInt() - 1;
		in.nextLine();
		Crop selectedCrop = crops.get(indexCrop);
		in.close();
		return selectedCrop;
	}
	
	public static void tendToCrop() {
		Scanner in = new Scanner(System.in);
		List<Crop> crops = playerFarm.getCrops();
		List<CropItem> cropItems = playerFarm.getCropItems();
		if (crops.size() == 0) {
			System.out.println("You have no crops to tend to.");
		}
		else {
			actions--; // reduce action count only if crop is tended to
			System.out.println("Enter the crop number you would you like to tend to:");
			Crop selectedCrop = selectCrop(crops);			
			System.out.println("Enter 0 to water crop or select item to use from your inventory: ");
			System.out.println("0 : Water");
			if (cropItems.size() == 0) {
				System.out.println("You don't own any crop items.");
			}
			else {
				for (int i = 0; i < cropItems.size(); i++) {
					System.out.println(i+1 + " : " + cropItems.get(i).getName());
				}
			}
			int indexItem = in.nextInt() - 1;
			in.nextLine();
			if (indexItem == -1) {
				selectedCrop.water();
			}
			else {
				CropItem selectedItem = cropItems.get(indexItem);
				selectedCrop.tendCrop(selectedItem);
				playerFarm.useItem(selectedItem);
			}
			
		}
		in.close();
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
			actions--; // reduce action count only if crop is harvested
			System.out.println("Enter the crop number you would you like to harvest:");
			Crop selectedCrop = selectCrop(harvestCrops);
			double sellPrice = selectedCrop.harvest();
			System.out.println(selectedCrop.getType() + " crop harvested and sold for $" + sellPrice + " at the local market.");
			playerFarm.addMoney(sellPrice);
			
		}
	}
	
	public static void playWithAnimals() {
		actions--;
		for (Animal animal : playerFarm.getAnimals()) {
			animal.play();
		}
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
					
					if ((actions == 0) && ((action > 4) && (action < 10))) { // sets action out of range if a counted action selected but no actions remaining
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
				
				// more actions
				
					
			}
			daysRemaining -= 1; // moved this out by one into "while (daysRemaining > 0)" loop
		}
		
	}
}
