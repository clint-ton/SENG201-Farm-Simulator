package main;
/**
 * Crop wheat, Price: 75, Sells for, 500, Takes 3 days to grow
 */
public class Corn extends Crop {
	
	public Corn() {
		// Pricing/harvest period adjustable to suit rest of game 
		super("Corn", 75.00, 500.00, 3);
	}

}
