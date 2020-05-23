package main;
import java.util.ArrayList;
import java.util.List;
/**
 * Contains player inventory, crops/animals and the various bonuses
 */
public class Farm {
	
	private String name;
	private String type;
	private Farmer farmer;
	private List<Crop> crops = new ArrayList<>();
	private List<Animal> animals = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	private double cropGrowthBonus; // initial bonuses specific to each type of farm
	private int animalHealthBonus; // change so can be set in constructor by subclass
	private double cropMoneyBonus;
	private double animalMoneyBonus;

	private double money;
	
//	public Farm(String farmName, String farmType, Farmer tmpFarmer, List<Crop> crops, List<Animal> animals) {
//		name = farmName;
//		type = farmType;
//		farmer = tmpFarmer;
//		money = 1000;
//	}
	
	
	public Farm(String type, double animalMoneyBonus, double cropMoneyBonus, double money) { // new constructor to account for farm types
		this.type = type;
		this.crops.addAll(crops);
		this.animalMoneyBonus = animalMoneyBonus;
		this.cropMoneyBonus = cropMoneyBonus;
		this.money = money;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public Farmer getFarmer() {
		return farmer;
	}
	/**
	 * Sets the farmer and adds the farmers bonuses to existing bonuses
	 * @param farmer to be used on the farm
	 */
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
		cropGrowthBonus += farmer.getCropBonus();
		animalHealthBonus += farmer.getAnimalBonus();
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
	
	public List<AnimalItem> getAnimalItems() {
		List<AnimalItem> animalItems = new ArrayList<AnimalItem>();
		for (Item item : items) {
			if (item.getType() == "Animal") {
				animalItems.add((AnimalItem) item);
			}
		}
		return animalItems;		
	}
	
	public List<CropItem> getCropItems() {
		List<CropItem> cropItems = new ArrayList<CropItem>();
		for (Item item : items) {
			if (item.getType() == "Crop") {
				cropItems.add((CropItem) item);
			}
		}
		return cropItems;
	}
	/**
	 * Gets only harvestable crops.
	 * @return list of crops
	 */
	public List<Crop> getHarvestCrops() {
		List<Crop> harvestCrops = new ArrayList<Crop>();
		for (Crop crop : crops) {
			if (crop.readyToHarvest()) {
				harvestCrops.add(crop);
			}
		}
		return harvestCrops;
	}
	
	
	public String toString() {
		return (type + " Farm: Animal Income Multiplier = " + animalMoneyBonus + " --- Crop Income Multiplier = " + cropMoneyBonus + " --- Starting Balance = $" + money);
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
	
	public double getCropGrowthBonus() {
		return cropGrowthBonus;
	}
	
	public int getAnimalHealthBonus() {
		return animalHealthBonus;
	}
	
	public double getAnimalMoneyBonus() {
		return animalMoneyBonus;
	}
	
	public double getCropMoneyBonus() {
		return cropMoneyBonus;
	}
		
	public double getMoney() {
		return money;
	}
	
	public void addMoney(double amount) {
		money += amount;
	}
	
	/**
	 * To be used when buying items, raises an exception if insufficient balance
	 * @param amount
	 */
	public void spendMoney(double amount) {
		if (money - amount >= 0) {
			money -= amount;
		}else {
			throw new IllegalArgumentException("Not enough money.");
		}
	}
	
	public void addCropGrowthBonus(double amount) {
		cropGrowthBonus += amount;
	}
	
	public void addAnimalHealthBonus(int amount) {
		animalHealthBonus += amount;
		if (animalHealthBonus > 14) { // limit so animals lose minimum 1 happiness/health daily
			animalHealthBonus = 14;
		}
	}
	/**
	 * Purchases the selected animal if enough money
	 * @param crop
	 */
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
	/**
	 * Purchases the selected crop if enough money
	 * @param crop
	 */
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
	/**
	 * Purchases the selected item if enough money
	 * @param crop
	 */
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
	/**
	 * Increases bonus to crops and animals
	 */
	public void tendLand() {
		addAnimalHealthBonus(2);
		addCropGrowthBonus(0.25);
	}
	/**
	 * Removes an item from farm inventory, to be called when item is used
	 * @param item to be used
	 */
	public void useItem(Item item) {
		items.remove(item);
	}
	
	public void deadAnimal(Animal animal) {
		animals.remove(animal);
	}
	
	public void removeCrop(Crop crop) {
		crops.remove(crop);
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
}
