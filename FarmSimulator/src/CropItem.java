
public class CropItem extends Item {
	
    private int growthBoost;
	
	public CropItem(String name, double price, int growthBoost) {
		super(name, "Crop", price);
		this.growthBoost = growthBoost;
	}
	
	public int getGrowthBoost() {
		return growthBoost;
	}

}
