package test;
import main.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FarmTest {

	@Test
	void farmStatusEmpty() {
		Farm farm = new Farm("Crop Growth", 2, 1, 1000);
		
		assertEquals("You have no crops or animals", farm.checkFarmStatus());
		}
	
	@Test
	void farmStatusNormalUse() {
		Farm farm = new Farm("Crop Growth", 2, 1, 1000);
		Goat goat = new Goat();
		Chicken chicken = new Chicken();
		Rice rice = new Rice();
		Berries berries = new Berries();

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
