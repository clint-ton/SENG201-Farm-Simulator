package test;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import Farm;
import Farmer;

class FarmTest {

	@Test
	void spendMoneyTest() {
		Farmer testFarmer = new Farmer("Nigel", 34, "Shearing");
		Farm testFarm = new Farm("test", "normal", testFarmer);
		
		testFarm.addMoney(100);
		assertEquals(100, testFarm.getMoney());
		assertThrows(IllegalArgumentException.class, testFarm.spendMoney(110));
		
		
	}
	

}
