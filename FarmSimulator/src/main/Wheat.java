package main;
/**
 * Crop wheat, Price: 100, Sells for, 1000, Takes 6 days to grow
 */
public class Wheat extends Crop {
	
	public Wheat() {
		// Pricing/harvest period adjustable to suit rest of game 
		super("Wheat", 100.00, 1000.00, 6);
	}

}
