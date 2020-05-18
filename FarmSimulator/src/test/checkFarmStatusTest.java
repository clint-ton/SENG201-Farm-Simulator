package test;
import main.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class checkFarmStatusTest {

	@Test
	void noCropsAnimals() {
		Farmer farmer = new Farmer("name", 20, "skill");
		Farm farm = new Farm("name", "type", farmer);
		
		assertEquals("You have no crops or animals", farm.checkFarmStatus());
		}
	
	@Test
	void normalUse() {
		Farmer farmer = new Farmer("name", 20, "skill");
		Farm farm = new Farm("name", "type", farmer);
		Goat goat = new Goat();
		Chicken chicken = new Chicken();
		Rice rice = new Rice();
		Berries berries = new Berries();
				
		farm.addMoney(999999);
		farm.purchaseAnimal(goat);
		farm.purchaseAnimal(chicken);
		farm.purchaseCrop(rice);
		farm.purchaseCrop(berries);
				
		assertEquals("Rice: Time Growing = 0, Time to Harvest = 8\n" + 
				"Berries: Time Growing = 0, Time to Harvest = 6\n" + 
				"Goat: Happiness = 50, Health = 80\n" + 
				"Chicken: Happiness = 50, Health = 80\n" , farm.checkFarmStatus());
	}
	
	

}
