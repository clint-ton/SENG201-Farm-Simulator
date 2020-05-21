package main;

public class CropItem extends Item {
	
    private double growthBoost;
	
	public CropItem(String name, double price, double growthBoost) {
		super(name, "Crop", price);
		this.growthBoost = growthBoost;
	}
	
	public double getGrowthBoost() {
		return growthBoost;
	}
	
	public String toString() {
		return (this.getName() + ": Type = " + this.getType() + " Growth Boost = " + growthBoost);
	}
		
	public String storeString() {
		return (this.getName() + Game.nln + "Type: " + this.getType() + Game.nln + "Growth Boost = " + growthBoost + " days" + Game.nln + "Price/Unit = $" + this.getPrice());
	}

}
