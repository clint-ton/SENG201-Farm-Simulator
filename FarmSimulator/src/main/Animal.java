package main;

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
	
	public String getType() {
		return type;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getIncome() {
		return income;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void feed(AnimalItem item) {
		health += item.getHealthBoost();
		if (health > 100) {
			health = 100;
		}
	}
	
	public void play() {
		happiness += 20;
		if (happiness > 100) {
			happiness = 100;
		}
	}
	
	public void loseHappiness() {
		// Daily loss of happiness - implement at start of day 
		happiness -= 5;
		if (happiness < 0) {
			happiness = 0;
		}
	}
	
	public void loseHealth() {
		// Daily loss of health
		health -= 5;
		if (health < 0) {
			health = 0;
		}
	}

}
