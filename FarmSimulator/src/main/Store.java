package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Store {
	private List<Crop> crops = new ArrayList<>();
	private List<Animal> animals = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	
	boolean inStore = true;
	
	public Store() {
		restock();
	}
	
	public void restock() {
		crops.clear();
		animals.clear();
		items.clear();
		
		Beans beans = new Beans();
		Berries berries = new Berries();
		Cabbage cabbage = new Cabbage();
		Corn corn = new Corn();
		Rice rice = new Rice();
		Wheat wheat = new Wheat();
		
		Chicken chicken = new Chicken();
		Cow cow = new Cow();
		Goat goat = new Goat();
		
		Fertiliser fertiliser = new Fertiliser();
		GrassFeed grassFeed = new GrassFeed();
		GrowthHormone growthHormone = new GrowthHormone();
		WeedSpray weedSpray = new WeedSpray();

		
		crops.addAll(Arrays.asList(beans, berries, cabbage, corn, rice, wheat));
		animals.addAll(Arrays.asList(chicken, cow, goat));
		items.addAll(Arrays.asList(fertiliser, grassFeed, growthHormone, weedSpray));
	}
	
	public void viewInventory(Farm farm) { // previously boolean
		
		int input;
		Object item;
		Scanner in = new Scanner(System.in);
//		Need to show more info 
		System.out.println("The following items are avalible\n");
		System.out.println("Crops: ");
		for (int i = 0; i < crops.size(); i++) {
			System.out.println(i + 1 + ") " + crops.get(i).getType());
		}
		System.out.println("\nAnimals: ");
		
		for (int i = 0; i < animals.size(); i++) {
			System.out.println(i + 1 + crops.size() + ") " + animals.get(i).getType());
		}
		System.out.println("\nItems: Crops items make a crop grow faster, and animal items make it happier");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(i + 1 + crops.size() +  + animals.size() + ") " + items.get(i).getName() + ": Type: " + items.get(i).getType());
		}
		
		System.out.println("\nExit\n14)\n");

		do {
			System.out.println("Which item are you interested in?");
			input = in.nextInt();
			in.nextLine();
			
		} while ((input <= 0) & (input > (crops.size() + animals.size() + items.size() + 1)));
		
		if (input == crops.size() + animals.size() + items.size() + 1) { // added temporary exit functionality for testing - can refine further
			inStore = false;
		} else if (input < crops.size()) {
			System.out.println("How many would you like to purchase?");
			int quantity = in.nextInt();
			in.nextLine();
			
			for (int i = quantity; i > 0; i--) {
				farm.purchaseCrop(crops.get(input-1));
				restock();
			}
		} else if (input > animals.size() + crops.size()) {
			System.out.println("How many would you like to purchase?");
			int quantity = in.nextInt();
			in.nextLine();
			
			for (int i = quantity; i > 0; i--) {
				farm.purchaseItem(items.get(input - crops.size() - animals.size() - 1));
				restock();
			}
		} else {
			System.out.println("How many would you like to purchase?");
			int quantity = in.nextInt();
			in.nextLine();
			
			for (int i = quantity; i > 0; i--) {
				farm.purchaseAnimal(animals.get(input - crops.size() - 1));
				restock();
			}
		}
		
		
		//return inStore;
	}
	
	public List<Crop> getStoreCrops() {
		return crops;
	}
	
	public List<Animal> getStoreAnimals() {
		return animals;
	}
	
	public List<Item> getStoreItems() {
		return items;
	}	
	
	public void visitStore(Farm farm) {
		int storeAction;
		Scanner in = new Scanner(System.in);
		while (true) {
			do {
				System.out.println("Welcome to the store! What would you like to do today?");
				
				System.out.println("1) View/Purchase store inventory");
				System.out.println("2) View player inventory");
				System.out.println("3) Exit");
	
				storeAction = in.nextInt();
				in.hasNextLine();
	
			}
			while ((storeAction < 0) & (storeAction > 4));
			if (storeAction == 1) {
				inStore = true;
				do {
					viewInventory(farm);
				}
				while (inStore);
			} 
			else if (storeAction == 2) {
				System.out.println(farm.viewInventory());
			}
			else if (storeAction == 3) {
				break;
			}
		}
		
	}

	public static void main(String[] args) {
		Farmer farmer = new Farmer("Name", 30, "Skill");
		Farm farm = new Farm("Name", "Type", farmer);
		Store store = new Store();
//		System.out.println(store.getStoreCrops());
//		farm.addMoney(99999);
//		farm.purchaseCrop(store.getStoreCrops().get(0));
//		System.out.println(store.getStoreCrops());
//		farm.getCrops().get(0).grow();
//		store = new Store();
//		farm.purchaseCrop(store.getStoreCrops().get(0));
//		for (int i = 0; i < farm.getCrops().size(); i++) {
//			System.out.println(farm.getCrops().get(i).getAgeDays());
//		}
//		store.viewInventory(farm);
//				
	}
}
