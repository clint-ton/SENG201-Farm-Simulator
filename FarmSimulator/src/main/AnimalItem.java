package main;
/**
 * Items to be used on Animals
 */
public class AnimalItem extends Item {
	
	private int healthBoost;
	
	public AnimalItem(String name, double price, int healthBoost) {
		super(name, "Animal", price);
		this.healthBoost = healthBoost;		
	}
	
	public int getHealthBoost() {
		return healthBoost;
	}
	
	public String toString() {
		return (this.getName() + ": Type = " + this.getType() + " Health Boost = " + healthBoost);
	}
	/**
	 * 	String used by the store
	 */
	public String storeString() {
		return (this.getName() + Game.nln + "Type: " + this.getType() + Game.nln + "Health Boost = " + healthBoost + " days" + Game.nln + "Price/Unit = $" + this.getPrice());
	}

}
