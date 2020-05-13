import java.util.ArrayList;
import java.util.List;

public class Store {
	private List<Crop> crops = new ArrayList<>();
	private List<Animal> animals = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	
	public List<Crop> getStoreCrops() {
		return crops;
	}
	
	public List<Animal> getStoreAnimals() {
		return animals;
	}
	
	public List<Item> getStoreItems() {
		return items;
	}
	
	
	
	public static void main(String[] args) {
		Farmer farmer = new Farmer("Name", 30, "Skill");
		Farm farm = new Farm("Name", "Type", farmer);
	
				
	}
}
