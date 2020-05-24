package main;
/**
 * Attributes and methods for Crops
 */
public class Crop {
	private String type;
	private double purchasePrice;
	private double sellPrice;
	private int harvestPeriod;
	private double ageDays;
	
	public Crop(String type, double purchasePrice, double sellPrice, int harvestPeriod) {
		this.type = type;
		this.purchasePrice = purchasePrice;
		this.sellPrice = sellPrice;
		this.harvestPeriod = harvestPeriod;
		this.ageDays = 0;
	}
	
	public String getType() {
		return type;	
	}
	
	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public double getSellPrice() {
		return sellPrice;
	}
	
	public int getHarvestPeriod() {
		return harvestPeriod;
	}
	
	public double getAgeDays() {
		return ageDays;
	}
	/**
	 * A String representation of the crop
	 */
	public String toString() {
		return (type + ": Days Growing = " + ageDays + " days" + "Days to Harvest =  " + (harvestPeriod - ageDays) + " days");
	}
	/**
	 * A string used by the store
	 * @return
	 */
	public String storeString() {
		return (type + Game.nln + "Harvest Period = " + harvestPeriod + " days" + Game.nln + "Sell Price = $" + sellPrice + Game.nln + "Price/Unit = $" + purchasePrice);
	}
	
	/**
	 * Uses an item to grow the crop (increase age)
	 * @param item to be used
	 */
	public void tendCrop(CropItem item) {
		ageDays = ageDays + item.getGrowthBoost();
		
	}
	/**
	 * Grows the crop without item cost
	 */
	public void water() {
		ageDays += 1;
	}
	/**
	 * Determines if a crop is ready for harvest
	 * @return True if ready to harvest else False
	 */
	public boolean readyToHarvest() {
		return (ageDays >= harvestPeriod);
	}
	/**
	 * Harvests a crop.
	 * @return Sale price
	 */
	public double harvest() {
		ageDays = 0;
		return sellPrice;
	}
	/**
	 * Grows the crop scaling to a bonus
	 * @param bonus, should be from farm/farmer type
	 */
	public void grow(double bonus) {
		ageDays += 1 + bonus;
	}
	

}
