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
import main.Farmer;
 
class CropTests {
   
    public Game setupTest() {
        Game game = new Game();
        Farmer farmer = new Farmer("Skill", 0, 0);
        Farm farm = new Farm("type", 0, 0, 9999);
        game.setPlayerFarm(farm, "name");
        Rice rice = new Rice();
        WeedSpray item = new WeedSpray();
        game.purchaseProduct(rice, 0, 1);
        game.purchaseProduct(item, 0, 99);
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
    	int actions = 2;
    	Game game = new Game();
        Crop selectedCrop = game.getPlayerFarm().getCrops().get(0);
        CropItem selectedItem = (CropItem) game.getPlayerFarm().getItems().get(0);
    	game.tendToCrop(selectedItem, selectedCrop);
    	assertEquals(game.getPlayerFarm().getCrops(),0);
    	assertEquals(game.getPlayerFarm().getItems(), 0);
    }
//    @Test
//    void test() {
//        fail("Not yet implemented");
//    }
//    @Test
//    void test() {
//        fail("Not yet implemented");
//    }
//    @Test
//    void test() {
//        fail("Not yet implemented");
//    }
//    @Test
//    void test() {
//        fail("Not yet implemented");
//    }
//   
 
}