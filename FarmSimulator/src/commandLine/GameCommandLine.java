//package commandLine;
//import main.*;
//import java.util.List;
//import java.util.Scanner;
//
//import commandLine.Store;
//
//import java.util.ArrayList;
//
//
//public class GameCommandLine {
//
//	private Farmer player;
//	private Farm playerFarm;
//	private int daysRemaining;
//	private int actions = 2;
//	private Store store;
//	private boolean nextDay;
//	
//	public static void main(String[] args) {     // for command line application
//	Game game = new Game();
//	UserInput selector = new UserInput(game);
//	Scanner in = new Scanner(System.in);
//	do {
//		System.out.println("How many days would you like your game to last? Please Select 5-10");
//		game.daysRemaining = in.nextInt();
//		in.nextLine(); //consumes the \n char leftover from nextInt()
//	}
//
//	while ((game.daysRemaining < 5) | (game.daysRemaining > 10));		
//	// TODO make sure names fit requirements
//	System.out.println("What is your name?");
//	String name = in.nextLine();
//	System.out.println("How old are you?");
//	int age = in.nextInt();
//	in.nextLine();
//	Farmer farmerType = selector.selectFarmer();
//	game.setPlayer(farmerType, name, age);
//	System.out.println("What would you like your farm to be called?");
//	String farmName = in.nextLine();
//	Farm farmType = selector.selectFarm();
//	game.setPlayerFarm(farmType, farmName);
//	game.store = new Store();
//	
//	while(game.daysRemaining > 0) {
//		System.out.println("Good Morning! You have " + game.daysRemaining + " days remaining.");
//		game.actions = 2;
//		game.nextDay = false;
//		
//		while(!game.nextDay) {
//			int action = 0;
//			System.out.println("You have " + game.actions + " actions available. " + Game.nln + "" + Game.nln + "");
//			do {
//				System.out.println("What would you like to do? (please enter a number)");
//				System.out.println("Free daily activities:");
//				System.out.println("1) Check crop/animal status");
//				System.out.println("2) Check farm bank account");
//				System.out.println("3) Visit the store");
//				System.out.println("4) Go to next day"); // Grouped free/counted actions together
//				System.out.println("Counted daily actions:");
//				System.out.println("5) Tend to a crop"); // added all actions here - can reduce action count within appropriate action methods
//				System.out.println("6) Harvest a crop");
//				System.out.println("7) Play with animals");
//				System.out.println("8) Feed animals");
//				System.out.println("9) Tend to farm land");
//				action = in.nextInt();
//				in.nextLine();
//				
//				if ((game.actions == 0) && ((action > 4) && (action < 10))) { // sets action out of range if a counted action is selected but no actions remaining
//					System.out.println("Sorry, you have no daily actions remaining. Please select a free option." + Game.nln + "");
//					action = 10;
//				}
//			}
//			while ((action <= 0) || (action > 9)); // is it meant to be (action <= 0) rather than (action > 0)?
//			if (action == 1) {
//				System.out.println(game.playerFarm.checkFarmStatus());
//			}
//			else if (action == 2) {
//				System.out.println("The farm currently has $" + game.playerFarm.getMoney() + " available");
//			}
//			else if (action == 3) {
//				game.store.visitStore(game.playerFarm);
//			}
//			else if (action == 4) {
//				if (game.actions > 0) {
//					System.out.println("Are you sure? You still have " + game.actions + " remaining actions. (Please enter yes or no)");
//					String answer = in.nextLine();
//					if (answer.startsWith("y")) {
//						game.nextDay = true;
//					}
//				} else {
//					game.nextDay = true;
//				}
//			}
//			else if (action == 5) {
//				Crop selectedCrop = selector.selectTendCrop(game.getPlayerFarm().getCrops());
//				if (selectedCrop != null) {
//					int itemIndex = selector.selectCropItem(game.getPlayerFarm().getCropItems());
//					if (itemIndex == -1) {
//						System.out.println(game.waterCrop(selectedCrop));
//					} else if (itemIndex != -2) {
//						CropItem selectedItem = game.getPlayerFarm().getCropItems().get(itemIndex);
//					    game.tendToCrop(selectedItem, selectedCrop);
//					}
//			    }
//			}
//			else if (action == 6) {
//				Crop selectedCrop = selector.selectHarvestCrop();
//				if (selectedCrop != null) {
//				    System.out.println(game.harvestCrop(selectedCrop));
//				}
//			}
//			else if (action == 7) {
//				if (game.getAnimals().size() == 0) {
//					System.out.println("You have no animals to play with.");
//				} else {
//					System.out.println(game.playWithAnimals());
//				}
//
//			}
//			else if (action == 8) {
//				AnimalItem selectedFood = selector.selectAnimalItem();
//				if (selectedFood != null) {
//					System.out.println(game.feedAnimals(selectedFood,  game.getPlayerFarm().getAnimals()));
//				}
//			}
//			else if (action == 9) {
//				System.out.println(game.tendLand());
//			}
//		}
//		
//		System.out.println(game.dailyChange()); // daily bonuses, growth, reduce day count
//	}
//  System.out.println("Congratulations, you finished the game!" + Game.nln + Game.nln);
//  System.out.println("You score a total of: " + Game.nln);
//  System.out.println(game.endGame());
//    System.out.println("Your final score is: ");
//  System.out.println(String.format("%.0f", endGameTotal()) + " points.");
//}
//}
//}
