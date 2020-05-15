package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Store {
	private List<Crop> crops = new ArrayList<>();
	private List<Animal> animals = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	
	public Store() {
		restock();
	}
	
	public void restock() {
		crops.clear();
		animals.clear();
		
		Beans beans = new Beans();
		Berries berries = new Berries();
		Cabbage cabbage = new Cabbage();
		Corn corn = new Corn();
		Rice rice = new Rice();
		Wheat wheat = new Wheat();
		
		Chicken chicken = new Chicken();
		Cow cow = new Cow();
		Goat goat = new Goat();
		
		crops.addAll(Arrays.asList(beans, berries, cabbage, corn, rice, wheat));
		animals.addAll(Arrays.asList(chicken, cow, goat));
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
				System.out.println("CLI works");
			} 
			else if (storeAction == 2) {
				System.out.println(farm.viewInventory());
			}
			else if (storeAction == 3) {
				break;
			}
		}
		
	}

//	public static void main(String[] args) {
//		Farmer farmer = new Farmer("Name", 30, "Skill");
//		Farm farm = new Farm("Name", "Type", farmer);
//		Store store = new Store();
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
				
	}

