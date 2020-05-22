package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Animal;

class AnimalTest {

	@Test
	void playTest() {
		Animal testAnimal = new Animal("Cow", 0, 0);
		testAnimal.play();
		assertEquals(testAnimal.getHappiness(), 70);
		testAnimal.play();
		assertEquals(testAnimal.getHappiness(), 90);
		testAnimal.play();
		assertEquals(testAnimal.getHappiness(), 100);
	}
	
	@Test
	void dailyBonusTest() {
		Animal testAnimal = new Animal("Cow", 0, 0);
		testAnimal.dailyBonus(0);
		assertEquals(testAnimal.getHealth(), 80);
		assertEquals(testAnimal.getHappiness(), 50);
		testAnimal.dailyBonus(5);
		assertEquals(testAnimal.getHealth(), 85);
		assertEquals(testAnimal.getHappiness(), 55);
		testAnimal.dailyBonus(25);
		assertEquals(testAnimal.getHealth(), 100);
		assertEquals(testAnimal.getHappiness(), 64);
		testAnimal.dailyBonus(25);
		testAnimal.dailyBonus(25);
		testAnimal.dailyBonus(25);
		testAnimal.dailyBonus(25);
		testAnimal.dailyBonus(25);
		testAnimal.dailyBonus(25);
		assertEquals(testAnimal.getHealth(), 100);
		assertEquals(testAnimal.getHappiness(), 100);


	}
	
	@Test
	void dailyLossTest() {
		Animal testAnimal = new Animal("Cow", 0, 0);
		assertEquals(testAnimal.dailyLoss(), "");
		assertEquals(testAnimal.getHealth(), 65);
		assertEquals(testAnimal.getHappiness(), 40);
		assertEquals(testAnimal.dailyLoss(), "");
		assertEquals(testAnimal.dailyLoss(), "");
		assertEquals(testAnimal.dailyLoss(), "");
		assertEquals(testAnimal.dailyLoss(), "Happiness level dropped to 0. Your animal lost the will to live.\n".trim());
		testAnimal.play();
		assertEquals(testAnimal.dailyLoss(), "Health level dropped to 0. Your animal's body could no longer function.\n".trim());
		
	}

}
