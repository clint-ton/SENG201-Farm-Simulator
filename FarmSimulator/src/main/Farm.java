package main;
import java.util.ArrayList;
import java.util.List;

public class Farm {
	
	private String name;
	private String type;
	private Farmer farmer;
	private List<Crop> crops = new ArrayList<>();
	private List<Animal> animals = new ArrayList<>();
	private List<Item> items = new ArrayList<>();

	private float money;
	
	public Farm(String farmName, String farmType, Farmer tmpFarmer) {
		name = farmName;
		type = farmType;
		farmer = tmpFarmer;
//		To set starting money, assuming this will be constant across games, probably wont be zero
		money = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public Farmer getFarmer() {
		return farmer;
	}
	
	public List<Crop> getCrops() {
		return crops;
	}
	
	public List<Animal> getAnimals() {
		return animals;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public String viewInventory() {
//		TODO group similar items together
		String result = "";
		for (int i = 0; i < items.size(); i++) {
			result += items.get(i).getName() + ": Used on = " + items.get(i).getType() + "s, Price = " + items.get(i).getPrice() + "\n";
		}
		
		if (result == "") {
			result = "You have no items at the moment.";
		}
		return result;
	}
	
	public float getMoney() {
		return money;
	}
	
	public void addMoney(int amount) {
		money += amount;
	}
	
	public void spendMoney(double amount) {
		if (money - amount >= 0) {
			money -= amount;
		}else {
			throw new IllegalArgumentException("Not enough money.");
		}
	}
	
	public String purchaseCrop(Crop crop) {
		try
		{
			spendMoney(crop.getPurchasePrice());
			crops.add(crop);
			return "Success, " + crop.getType() + " purchased for " + crop.getPurchasePrice();
		} catch (Exception e) {
			return "Not enought money for that.";
		}
	}

	public String purchaseAnimal(Animal animal) {
		try
		{
			spendMoney(animal.getPrice());
			animals.add(animal);
			return "Success, " + animal.getType() + " purchased for " + animal.getPrice();
		} catch (Exception e) {
			return "Not enought money for that.";
		}		
	}
	public String purchaseItem(Item item) {
		try
		{
			spendMoney(item.getPrice());
			items.add(item);
			return "Success, " + item.getName() + " purchased for " + item.getPrice();
		} catch (Exception e) {
			return "Not enought money for that.";
		}		
	}
	
	public void tendLand() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public void useItem(Item item) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public String checkFarmStatus() {
		String result = "";
		for (int i = 0; i < crops.size(); i++) {
			result += crops.get(i).getType() + ": Time Growing = " + crops.get(i).getAgeDays() + ", Time to Harvest = " + (crops.get(i).getHarvestPeriod() - crops.get(i).getAgeDays()) + "\n";
		}
		for (int i = 0; i < animals.size(); i++) {
			result += animals.get(i).getType() + ": Happiness = " + animals.get(i).getHappiness() + ", Health = " + animals.get(i).getHealth() + "\n";
		}
		if (result == "") {
			result = "You have no crops or animals";
		}
		return result;
	}
	
	
//	public static void main (String[] args) {
//		Farmer player = new Farmer("Name", 30, "None");
//		Farm playerFarm = new Farm("Name", "None", player);
//		playerFarm.addMoney(150);
//		Rice rice = new Rice();
//		playerFarm.purchaseCrop(rice);
//		System.out.println(playerFarm.crops);
//		System.out.println(playerFarm.getMoney());
//	}
}
