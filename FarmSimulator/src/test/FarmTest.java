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
				
		assertEquals("Rice: Time Growing = 0.0, Time to Harvest = 8.0\n" + 
				"Berries: Time Growing = 0.0, Time to Harvest = 6.0\n" + 
				"Goat: Happiness = 50, Health = 80\n" + 
				"Chicken: Happiness = 50, Health = 80\n" , farm.checkFarmStatus());
	}
	
	@Test
	void purchaseNormal() {
		Farm playerFarm = new Farm("Type", 1, 1, 1000);
		Rice rice = new Rice();
		playerFarm.purchaseCrop(rice);
		assertEquals(playerFarm.getCrops().size(), 1);
		assertEquals(playerFarm.getMoney(), 850);
	}
	
	@Test
	void purchaseNotEnoughMoney() {
		Farm playerFarm = new Farm("Type", 1, 1, 50);
		Rice rice = new Rice();
		playerFarm.purchaseCrop(rice);
		assertEquals(playerFarm.getCrops().size(), 0);
		assertEquals(playerFarm.getMoney(), 50);
	}
	
	@Test
	void harvestableCrops() {
		Farm playerFarm = new Farm("Type", 1, 1, 9999);
		assertTrue(playerFarm.getHarvestCrops().isEmpty());
		playerFarm.purchaseCrop(new Corn());
		assertTrue(playerFarm.getHarvestCrops().isEmpty());
		Crop crop = playerFarm.getCrops().get(0);
		crop.water();
		crop.water();
		crop.water();
		assertEquals(playerFarm.getHarvestCrops().size(), 1);
		playerFarm.purchaseCrop(new Corn());
		assertEquals(playerFarm.getHarvestCrops().size(), 1);

	}
	
	

}
