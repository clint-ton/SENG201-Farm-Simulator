package test;
 
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.Test;
 
import main.Game;
import main.GrowthHormone;
import main.Item;
import main.Rice;
import main.WeedSpray;
import main.Crop;
import main.CropItem;
import main.Farm;
import main.Farm1;
import main.Farmer;
import main.Farmer2;
 
class CropTests {
   
    public Game setupTest() {
        Game game = new Game();
        Farmer player;
        Farmer farmer = new Farmer2();
        game.setPlayer(farmer, "name", 12);
        Farm farm = new Farm1();
        game.setPlayerFarm(farm, "name");
        Rice rice = new Rice();
        WeedSpray item = new WeedSpray();
        game.purchaseProduct(rice, 0, 1);
        game.purchaseProduct(item, 0, 1);
        return game;
    }
   
    @Test
    void waterCrop() {
        Game game = setupTest();
        Crop selectedCrop = game.getPlayerFarm().getCrops().get(0);
        game.waterCrop(selectedCrop);
        assertEquals(selectedCrop.getAgeDays(), 1);
    }
   
    @Test
    void tendToCrop() {
    	Game game = setupTest();
        Crop selectedCrop = game.getPlayerFarm().getCrops().get(0);
        CropItem selectedItem = (CropItem) game.getPlayerFarm().getItems().get(0);
    	game.tendToCrop(selectedItem, selectedCrop);
    	assertEquals(game.getPlayerFarm().getCrops().get(0).getAgeDays(),1);
    	assertTrue(game.getPlayerFarm().getItems().isEmpty());
    	assertEquals(game.getActions(), 1);
    }
    @Test
    void harvestCrop() {
        Game game = setupTest();
        Crop selectedCrop = game.getPlayerFarm().getCrops().get(0);
        selectedCrop.water();
        System.out.println(selectedCrop.getSellPrice());
        System.out.println(game.getPlayerFarm().getMoney());
        System.out.println(game.harvestCrop(selectedCrop));
        System.out.println(game.getPlayerFarm().getMoney());
    	assertEquals(game.getActions(), 1);
    	assertTrue(game.getPlayerFarm().getMoney() > 2000);
    	assertTrue(game.getPlayerFarm().getCrops().isEmpty());

    }

 
}