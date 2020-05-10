import java.util.ArrayList;

public class Crop {
	private String type;
	private double purchasePrice;
	private double sellPrice;
	private int harvestPeriod;
	private int ageDays;
	
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
	
	public int getAgeDays() {
		return ageDays;
	}
	
	public void tendCrop(CropItem item) {
		ageDays = ageDays + item.getGrowthBoost();
	}
	
	public void water() {
		ageDays += 1;
	}
	
	public double harvest() {
		ageDays = 0;
		return sellPrice;
	}
	
	public void grow() {
		ageDays += 1;
	}
	
//	public static void main(String[] args) {
//	    Wheat wheat = new Wheat();
//		wheat.water();
//		wheat.grow();
//		Fertiliser item1 = new Fertiliser();
//		wheat.tendCrop(item1);
//		
//		while (wheat.getAgeDays() < wheat.getHarvestPeriod()) {
//			wheat.grow();	
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
