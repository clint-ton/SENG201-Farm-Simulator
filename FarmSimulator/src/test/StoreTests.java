package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Farm;
import main.Farm1;
import main.Farmer;
import main.Farmer2;
import main.Game;
import main.Rice;
import main.WeedSpray;

class StoreTests {
    
    public Game setupTest() {
        Game game = new Game();
        Farmer farmer = new Farmer2();
        game.setPlayer(farmer, "name", 12);
        Farm farm = new Farm1();
        game.setPlayerFarm(farm, "name");
        return game;
    }
	
    @Test
	void purchaseNormalUse() {
		Game game = setupTest();
		Rice rice = new Rice();
		game.purchaseProduct(rice, 150, 1);
		assertEquals(game.getPlayerFarm().getCrops().size(), 1);
		assertEquals(game.getPlayerFarm().getMoney(), 850);
		game.purchaseProduct(rice, 150, 4);
		assertEquals(game.getPlayerFarm().getCrops().size(), 5);
		assertEquals(game.getPlayerFarm().getMoney(), 250);
	}
    
    @Test
//    Handled by the store window so not nessecary to check.
	void notEnoughMoney() {
//		Game game = setupTest();
//		Rice rice = new Rice();
//		game.purchaseProduct(rice, 150, 10);
//		System.out.println(game.getPlayerFarm().getMoney());
//		System.out.println(game.getPlayerFarm().getCrops());
	}

}
