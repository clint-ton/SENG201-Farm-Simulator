import java.util.List;
import java.util.Scanner;

public class Game {
	Farmer player;
	static Farm playerFarm;
	static int gameLength;
	static int actions;
	
	//Probably doesn't work or look good, I'll test it all tomorrow
	
	public static void checkFarmStatus() { // will print status for now, however can be changed to return something to better fit a GUI
		List<Crop> crops = playerFarm.getCrops();
		for (int i = 0; i < crops.size(); i++) {
			System.out.println(crops.get(i).getType() + ": Time Growing : " + crops.get(i).getAgeDays() + " : Time to Harvest : " + (crops.get(i).getHarvestPeriod() - crops.get(i).getAgeDays()));
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("How many days would you like your game to last? Please Select 5-10");
			gameLength = in.nextInt();
		}
		while ((gameLength < 5) | (gameLength > 10));
		in.nextLine(); //consumes the \n char leftover from nextInt()
		
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
				System.out.println("You have " + actions + " actions avalible.");
			}
		}
		
	}
}
