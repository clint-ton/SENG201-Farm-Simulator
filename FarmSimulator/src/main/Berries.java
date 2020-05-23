package main;
/**
 * Crop Berries, Price: 50, Sells for, 800, Takes 6 days to grow
 */
public class Berries extends Crop {
	
	public Berries() {
		// Pricing/harvest period adjustable to suit rest of game 
		super("Berries", 50.00, 800.00, 6);
	}

}
