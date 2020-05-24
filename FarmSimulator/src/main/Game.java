package main;
import java.util.List;
import java.util.Scanner;

import commandLine.Store;

import java.util.ArrayList;
/**
 * Contains methods for game actions starts the main game.
 * @author jke9, cjw237
 *
 */
public class Game {
	private Farmer player;
	private Farm playerFarm;
	private int daysRemaining;
	private int actions = 2;
	private int day = 1;

//	private boolean nextDay;
	public static String nln = System.lineSeparator();  // newline character for specific machine
	
	public Game() { //Farmer farmer, Farm farm) {
//		player = farmer;
//		playerFarm = farm;
	}
	
	public Farmer getPlayer() {
		return player;
	}
	
	public int getActions() {
		return actions;
	}
	
	public Farm getPlayerFarm() {
		return playerFarm;
	}
	
	public int getDaysRemaining() {
		return daysRemaining;
	}
	
	public int getDay() {
		return day;
	}
	
	/**
	 * Creates the player, called from setup window
	 * @param farmer Player character object
	 * @param name Player name
	 * @param age Player age
	 */
	public void setPlayer(Farmer farmer, String name, int age) {
		player = farmer;
		player.setAge(age);
		player.setName(name);
	}
	/**
	 * Creates the player Farm
	 * @param farm Player farm object
	 * @param name Farm name
	 */
	public void setPlayerFarm(Farm farm, String name) {
		playerFarm = farm;
		playerFarm.setName(name);
		playerFarm.setFarmer(player);
	}
	
	public void setDaysRemaining(int days) {
		daysRemaining = days;
	}
	
	/**
	 * Waters a crop (using the method from crop) and reduces actions
	 * @param selectedCrop
	 */
	public String waterCrop(Crop selectedCrop) {
		selectedCrop.water();
		actions--;
		return ("You watered your " + selectedCrop.getType() + " crop." + nln + "It is growing fast and healthy.");
	}
	
	/**
	 * Purchases a quantity of a certain crop, called from purchaseProduct();
	 * @param crop
	 * @param quantity
	 */
	public String purchaseCrops(Crop crop, int quantity) {
		String type = "";
		if (crop instanceof Cabbage) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Cabbage());
				type = crop.getType();
			}
		} else if (crop instanceof Berries) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Berries());
				type = crop.getType();
			}
		} else if (crop instanceof Corn) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Corn());
				type = crop.getType();
			}
		} else if (crop instanceof Rice) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Rice());
				type = crop.getType();
			}
		} else if (crop instanceof Wheat) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Wheat());
				type = crop.getType();
			}
		} else {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseCrop(new Beans());
				type = crop.getType();
			}
		}
		return type;
	}
	/**
	 * Purchases a quantity of a certain Animal, called from purchaseProduct();
	 * @param animal
	 * @param quantity
	 */
	public String purchaseAnimals(Animal animal, int quantity) {
		String type = "";
		if (animal instanceof Cow) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseAnimal(new Cow());
				type = animal.getType();
			}
		} else if (animal instanceof Chicken) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseAnimal(new Cow());
				type = animal.getType();
			}
		} else {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseAnimal(new Goat());
				type = animal.getType();
			}
		}
		return type;
	}
	/**
	 * Purchases a quantity of a certain item, called from purchaseProduct();
	 * @param item
	 * @param quantity
	 */
	public String purchaseItems(Item item, int quantity) {
		String type = "";
		if (item instanceof Fertiliser) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new Fertiliser());
				type = item.getName();
			}
		} else if (item instanceof WeedSpray) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new WeedSpray());
				type = item.getName();
			}
		} else if (item instanceof NutrientBoost) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new NutrientBoost());
				type = item.getName();
			}
		} else if (item instanceof GrowthHormone) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new GrowthHormone());
				type = item.getName();
			}
		} else if (item instanceof GrassFeed) {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new GrassFeed());
				type = item.getName();
			}
		} else {
			for (int i = 0; i < quantity ; i++) {
				playerFarm.purchaseItem(new ImmuneBoost());
				type = item.getName();
			}
		}
		return type;
	}
	/**
	 * Purchases a product in a given quantity
	 * @param product a crop, animal or item
	 * @param price only used for the return string, will usually be an items price attribute
	 * @param quantity
	 * @return A string showing what was purchased
	 */
	public String purchaseProduct(Object product, double price, int quantity) {
		String type;
		if (product instanceof Crop) {
			type = purchaseCrops((Crop) product, quantity);
		} else if (product instanceof Animal) {
			type = purchaseAnimals((Animal) product, quantity);
		} else {
			type = purchaseItems((Item) product, quantity);
		}
		return ("Success - " + quantity + " " + type + " purchased for $" + price*quantity);
	}
	
	
	/**
	 * Tends to a crop using the crop method tendCrop, and reduces actions
	 * @param selectedItem
	 * @param selectedCrop
	 * @return A string showing what was done
	 */
	public String tendToCrop(CropItem selectedItem, Crop selectedCrop) {
		selectedCrop.tendCrop(selectedItem);
		playerFarm.useItem(selectedItem);
		actions--; // reduce action count only if crop is tended to
	    return ("You used " + selectedItem.getName() + " on your " + selectedCrop.getType() + " crop." + nln + "It is growing fast and healthy.");
	}
	
	/**
	 * Harvests a crop, crediting the player and removing the crop, reducing action
	 * @param selectedCrop
	 * @return A string showing what was done
	 */
	public String harvestCrop(Crop selectedCrop) {
		String message = "";
		double sellPrice = selectedCrop.harvest() * playerFarm.getCropMoneyBonus();
		message += (selectedCrop.getType() + " crop harvested and sold for $" + sellPrice + " at the local market.");
		playerFarm.addMoney(sellPrice);
		playerFarm.removeCrop(selectedCrop);
		actions--;
		return message;
	}
	/**
	 * Plays with all animals, and reduces available actions
	 * @return A string showing what was done
	 */
	public String playWithAnimals() {
		actions--;
		for (Animal animal : playerFarm.getAnimals()) {
			animal.play();
		}
		return ("Your animals are filled with joy!" + nln + "Animal Happiness = +20 Points");
	}
	
	/**
	 * Feeds all animals the given item, then removes it, reducing actions
	 * @param food
	 * @param animals
	 * @return A string saying how many animals were fed what
	 */
	public String feedAnimals(AnimalItem food, List<Animal> animals) {
		String message = "";
		for (int j = 0; j < animals.size(); j++) { // need to decide if food items feed all owned animals or up to a certain amount
			animals.get(j).feed(food);
		}
		message += ("You fed " + animals.size() + " animals with " + food.getName() + "." + nln + "They are well fed and nourished." + nln);
		playerFarm.useItem(food);
		
		actions--;
		return message;
	}	
	/**
	 * Tends farmland, using tendLand(); method, reducing actions
	 * @return a message saying what was done
	 */
	public String tendLand() {
		String message = "";
		actions--;
		playerFarm.tendLand();
		message += "Your farm is looking tidy and well maintained after tending to the land." + nln;
		message += "Crop Growth Bonus: +0.25 Days" + nln;
		message += "Animal Health Bonus: +2 Points" + nln;
		message += "Your animals will be healthier and happier, and your crops will grow faster." + nln;
		return message;
	}
	/**
	 * Grants a money bonus for each animal, intended to be run on day end
	 */
	public void moneyBonus() { // daily money bonus for animal happiness/health
		for (Animal animal : playerFarm.getAnimals()) {
			double bonus = ((animal.getIncome()) * playerFarm.getAnimalMoneyBonus()); 
			playerFarm.addMoney(bonus);
		}
	}
	/**
	 * Grants a bonus to animal health, intended to be used each day
	 */
	public void animalHealthBonus() { 
		for (Animal animal : playerFarm.getAnimals()) {
			animal.dailyBonus(playerFarm.getAnimalHealthBonus());
		}
	}
	/**
	 * Deducts daily loss of health/happiness for each animal
	 * and removes the animal if either hit zero
	 * @return A string showing how many animals died, and gives advice on how to prevent
	 */
	public String animalLoss() {
		boolean death = false;
		String message = "";
		int count = 0;
		List<Animal> deadAnimals = new ArrayList<Animal>();
		for (int i = 0; i < playerFarm.getAnimals().size(); i++) {
			boolean deadAnimal = playerFarm.getAnimals().get(i).dailyLoss();
			if (deadAnimal) {
				count+=1;
				death = true;
				deadAnimals.add(playerFarm.getAnimals().get(i));
			}	
		}
		if (death) { 
			for (Animal deadAnimal : deadAnimals) {
				playerFarm.deadAnimal(deadAnimal);
			}
			message = (count + " of your animals died overnight due to happiness and/or health levels dropping below 0." + nln);
			message += ("Animals need nourishment and joy to survive on your farm." + nln);
			message += ("Feed and play with your animals regularly to sustain their health and happiness." + nln);
		}	
		return message;
	}
	/**
	 * Grows each crop according to the farm bonus, intended to be used daily
	 */
	public void cropGrowth() {
		for (Crop crop : playerFarm.getCrops()) {
			crop.grow(playerFarm.getCropGrowthBonus());
		}
	}
	/**
	 * End of day change, resets actions, progresses game, and 
	 * calls end of day functions for animals/crops
	 * @return The dead animal message from animalLoss();
	 */
	public String dailyChange() {
		daysRemaining -= 1;
		day += 1;
		actions = 2;
		String message = "";
		moneyBonus();
		animalHealthBonus();
		if (daysRemaining > 0) {
			message = animalLoss();
		}
		cropGrowth();		
		return message;
	}
	/**
	 * Calculates end game points for animals
	 * @return score
	 */
	public double endGameAnimals() {
		double points = 0;
		for (Animal animal : playerFarm.getAnimals()) {
			points += (animal.getHappiness() + animal.getHealth()) * 0.5  * playerFarm.getAnimalMoneyBonus();
		}
		return points;
	}
	/**
	 * Calculate end game points for Crops
	 * @return score
	 */
	public double endGameCrops() {
		double points = 0;
		for (Crop crop : playerFarm.getCrops()) {
			points += (crop.getAgeDays()/crop.getHarvestPeriod()) * crop.getSellPrice() * playerFarm.getCropMoneyBonus()*0.5; // scaled down points for partially grown crops
		}
		return points;
	}
	/**
	 * Calculates total end game score
	 * @return total score
	 */
	public double endGameTotal() {
		return (endGameAnimals() + endGameCrops() + playerFarm.getMoney());
	}
	/**
	 * Generates a message to show the player at the end of the game
	 * @return An end game message
	 */
	public String endGameMessage() {
		String message = "";
		double animalPoints = endGameAnimals();
		double cropPoints = endGameCrops();
		double moneyPoints = playerFarm.getMoney();
		message += (String.format("%.0f", animalPoints) + " points from your final animal health and happiness levels." + nln);
		message += (String.format("%.0f", cropPoints) + " points from the crops you own that are still growing." + nln + "");
		message += (String.format("%.0f", moneyPoints) + " points from the final total dollars in your farm's bank account." + nln + nln);
		return message;
	}
	
	
	public void startGameLaunch() {
		StartGameWindow window = new StartGameWindow(this);
	}
	
	public void mainGameLaunch() {
		MainGameWindow window = new MainGameWindow(this);
		
	}
	
	public void tendCropLaunch() {
		TendCropWindow window = new TendCropWindow(this);
	}
	
	public void feedAnimalLaunch() {
		FeedAnimalWindow window = new FeedAnimalWindow(this);
	}
	
	public void harvestCropLaunch() {
		HarvestCropWindow window = new HarvestCropWindow(this);
	}
	
	public void storeLaunch() {
		StoreWindow window = new StoreWindow(this);
	}
	
	public void inventoryLaunch() {
		InventoryWindow window = new InventoryWindow(this);
	}
	
	public void statusLaunch() {
		StatusWindow window = new StatusWindow(this);
	}
	
	public void endGameLaunch() {
		EndGameWindow window = new EndGameWindow(this);
	}
	
	public void restartGame() {
		Game game = new Game();
		game.startGameLaunch();
	}
	
	public static void main(String[] args) {     // for GUI application
		Game game = new Game();
		game.startGameLaunch();
	}
}
