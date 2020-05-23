package main;
/**
 * Contains the attributes and methods common
 * to all animals
 * 
 *
 */

public class Animal {
	
	private String type;
	private double price;
	private double income;
	private int happiness;
	private int health;
	
	public Animal(String type, double price, double income) {
		this.type = type;
		this.price = price;
		this.income = income;
		this.happiness = 50; // Starting average happiness, can reach max of 100 or min of 0
		this.health = 80; // Bought health of 80, max of 100 min of 0
	}
	
	/**
	 * Getter for animal type (cow, goat, etc.)
	 *@return The animals type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter for price (purchase cost)
	 * @return The price of the animal
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Getter for daily income
	 * @return The animals daily income
	 */
	public double getIncome() {
		return income;
	}
	/**
	 * Getter for animal happiness
	 * @return The animals happiness level
	 */
	public int getHappiness() {
		return happiness;
	}
	/**
	 * Getter for health
	 * @return The animals health
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * A textual representation of the animals state
	 */
	public String toString() {
		return(type + ": Health = " + health + " Happiness = " + happiness);
	}
	
	/**
	 * Textual representation to be used by the store
	 */
	public String storeString() {
		return (type + Game.nln + "Daily Income = $" + income + Game.nln + "Price/Unit = $" + price);
	}
	
	/**
	 * Uses the specified item granting a boost to health
	 * @param item to be used
	 */
	public void feed(AnimalItem item) {
		health += item.getHealthBoost();
		if (health > 100) {
			health = 100;
		}
	}
	/**
	 * Increases happiness
	 */
	public void play() {
		happiness += 20;
		if (happiness > 100) {
			happiness = 100;
		}
	}
	/**
	 * Gives a bonus to happiness and health maxing out at 100
	 * @param bonus to be given, capped at 9 for happiness
	 */
	public void dailyBonus(int bonus) {
		health += bonus;
		if (bonus > 9) {
			happiness += 9;
		} else {
		    happiness += (bonus); // adjusted for difference in daily losses
		}
		if (health > 100) {
			health = 100;
		}
		if (happiness > 100) {
			happiness = 100;
		}
	}
	/**
	 * Decreases happiness and health by a set amount for daily decay
	 * @return true if dead, otherwise
	 */
	public boolean dailyLoss() {
		// Daily loss of happiness/health - implement at start of day 
		boolean death = false;
		happiness -= 10;
		if (happiness <= 0) {
			death = true;
		}
		health -= 15;
		if (health <= 0) {
			death = true;
		}
		return death;
	}
	

}
