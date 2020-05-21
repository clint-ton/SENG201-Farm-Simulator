package main;

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
	
	public String toString() {
		return (type + ": Days Growing = " + ageDays + " days" + "Days to Harvest =  " + (harvestPeriod - ageDays) + " days");
	}
	
	public String storeString() {
		return (type + Game.nln + "Harvest Period = " + harvestPeriod + " days" + Game.nln + "Sell Price = $" + sellPrice + Game.nln + "Price/Unit = $" + purchasePrice);
	}
	
	
	public void tendCrop(CropItem item) {
		ageDays = ageDays + item.getGrowthBoost();
		
	}
	
	public void water() {
		ageDays += 1;
	}
	
	public boolean readyToHarvest() {
		return (ageDays >= harvestPeriod);
	}
	
	public double harvest() {
		ageDays = 0;
		return sellPrice;
	}
	
	public void grow(double bonus) {
		ageDays += 1 + bonus;
	}
	
//	public static void main(String[] args) {
//	    Wheat wheat = new Wheat();
//		wheat.water();
//		Object j = wheat;
//		System.out.println(j instanceof Wheat);
//		Fertiliser item1 = new Fertiliser();
//		wheat.tendCrop(item1);
//		Wheat x = (Wheat) j;
//		
//		while (wheat.getAgeDays() < wheat.getHarvestPeriod()) {
//			//wheat.grow();	
//		}
//
//		System.out.println("Number of days to harvest: " + wheat.getAgeDays());
//		double money = 0;
//		money += wheat.harvest();
//		System.out.println("Money returned from harvest: " + money);
//
//		
//	}

}
