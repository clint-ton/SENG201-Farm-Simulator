package main;

public class AnimalItem extends Item {
	
	private int healthBoost;
	
	public AnimalItem(String name, double price, int healthBoost) {
		super(name, "Animal", price);
		this.healthBoost = healthBoost;		
	}
	
	public int getHealthBoost() {
		return healthBoost;
	}

}
