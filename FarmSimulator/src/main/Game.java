package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
	Farmer player;
	static Farm playerFarm;
	static int gameLength;
	static int actions;
	
	public Game(Farmer farmer, Farm farm) {
		player = farmer;
		playerFarm = farm;
	}
	
	public static String checkFarmStatus() {
		List<Crop> crops = playerFarm.getCrops();
		List<Animal> animals = playerFarm.getAnimals();
		String result = "";
		for (int i = 0; i < crops.size(); i++) {
			result += crops.get(i).getType() + ": Time Growing : " + crops.get(i).getAgeDays() + " : Time to Harvest : " + (crops.get(i).getHarvestPeriod() - crops.get(i).getAgeDays()) + "\n";
		}
		for (int i = 0; i < animals.size(); i++) {
			result += animals.get(i).getType() + ": Happiness : " + animals.get(i).getHappiness() + " : Time to Harvest : " + (crops.get(i).getHarvestPeriod() - crops.get(i).getAgeDays()) + "\n";
		}
		if (result == "") {
			result = "You have no crops or animals";
		}
		return result;
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
		Farmer player = new Farmer(name, age, "Skill");
		System.out.println("What would you like your farm to be called?");
		String farmName = in.nextLine();
		Farm playerFarm = new Farm(farmName, "Type", player);
		
		while(gameLength > 0) {
			System.out.println("Good Morning! You have " + gameLength + " days remaining");
			actions = 2;
			while(actions > 0) {
				int action = 0;
				System.out.println("You have " + actions + " actions avalible.");
				do {
					System.out.println("What would you like to do? (please enter a number");
					action = in.nextInt();
					in.hasNextLine();
				}
				while ((action > 0) & (action > 2)); // needs to be changed with every new action
				if (action == 1) {
					checkFarmStatus();
				}
			}
		}
		
	}
}
