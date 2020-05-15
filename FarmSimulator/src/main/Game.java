package main;
import java.util.Scanner;

public class Game {
	static Farmer player;
	static Farm playerFarm;
	static int gameLength;
	static int actions;
	static Store store;
	
	public Game(Farmer farmer, Farm farm) {
		player = farmer;
		playerFarm = farm;
	}
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("How many days would you like your game to last? Please Select 5-10");
			gameLength = in.nextInt();
			in.nextLine(); //consumes the \n char leftover from nextInt()
		}

		while ((gameLength < 5) | (gameLength > 10));		
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
		
		while(gameLength > 0) {
			System.out.println("Good Morning! You have " + gameLength + " days remaining.");
			actions = 2;
			while(actions > 0) { // needs to be changed otherwise can't do free actions
				int action = 0;
				System.out.println("You have " + actions + " actions avalible. \n\n");
				do {
					System.out.println("What would you like to do? (please enter a number)");
					
					System.out.println("1) Check crop/animal status");
					System.out.println("2) Check farm bank account");
					System.out.println("3) Visit the store");
					action = in.nextInt();
					in.hasNextLine();
				}
				while ((action > 0) & (action > 4)); // needs to be changed with every new action
				if (action == 1) {
					System.out.println(playerFarm.checkFarmStatus());
				}
				else if (action == 2) {
					System.out.println("The farm currently has $" + playerFarm.getMoney() + " avalible");
				}
				else if (action == 3) {
					store.visitStore(playerFarm);
				}
			}
		}
		
	}
}
