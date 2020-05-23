package commandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Animal;
import main.AnimalItem;
import main.Crop;
import main.CropItem;
import main.Farm;
import main.Farm1;
import main.Farm2;
import main.Farm3;
import main.Farm4;
import main.Farmer;
import main.Farmer1;
import main.Farmer2;
import main.Game;
/**
 * @deprecated
 */
public class UserInput {
	
	private Game game;
	
	
	public UserInput(Game game) {
		this.game = game;		
	}
	
	
	public Farmer selectFarmer() {
		Scanner in = new Scanner(System.in);
		int skill;
		Farmer selectedFarmer;
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
			selectedFarmer = farmer1;
		} else {
			selectedFarmer = farmer2;
		}
		return selectedFarmer;
	}
	
	public Farm selectFarm() {
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
		Farm selectedFarm = farmTypes.get(farmNum-1);
		return selectedFarm;
	}
	
	
	public Crop selectCrop(List<Crop> crops) {
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
	
	public int selectCropItem(List<CropItem> items) {
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
	
	
	public Crop selectTendCrop(List<Crop> crops) {	
		Scanner in = new Scanner(System.in);
		Crop selectedCrop = null;
		if (crops.size() == 0) {
			System.out.println("You have no crops to tend to.");
		}
		else {
			System.out.println("Enter the crop number you would you like to tend to:\n");
			selectedCrop = selectCrop(crops);		
		}
		return selectedCrop;
	}
	
	
	public Crop selectHarvestCrop() {
		Crop selectedCrop = null;
		List<Crop> harvestCrops = game.getPlayerFarm().getHarvestCrops();
		if (harvestCrops.size() == 0) {
			System.out.println("You own no crops that are ready for harvest.");
		}
		else {
			System.out.println("Enter the crop number you would you like to harvest:\n");
			selectedCrop = selectCrop(harvestCrops);
		}
		return selectedCrop;
		
	}

	public AnimalItem selectAnimalItem() {
		Scanner in = new Scanner(System.in);
		AnimalItem food = null;
		List<Animal> animals = game.getPlayerFarm().getAnimals();
		List<AnimalItem> foodItems = game.getPlayerFarm().getAnimalItems();
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
				
				food = foodItems.get(foodIndex);
	        }
		}
		return food;
	}

}
