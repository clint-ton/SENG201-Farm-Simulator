//import java.util.ArrayList;
//import java.util.List;

public class Farm {
	
	private String name;
	private String type;
	private Farmer farmer;
//	private List<Crop> crops = new ArrayList<>();
//	private List<Animal> animals = new ArrayList<>();
	private float money;
	
	public Farm(String farmName, String farmType, Farmer tmpFarmer) {
		name = farmName;
		type = farmType;
		farmer = tmpFarmer;
//		To set starting money, assuming this will be constant across games, probably wont be zero
		money = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public Farmer getFarmer() {
		return farmer;
	}
	
	public float getMoney() {
		return money;
	}
	
	public void addMoney(int amount) {
		money += amount;
	}
	
	public void spendMoney(int amount) {
		if (money - amount >= 0) {
			money -= amount;
		}else {
			throw new IllegalArgumentException("Not enough money.");
		}
	}
	
	public void tendLand() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
