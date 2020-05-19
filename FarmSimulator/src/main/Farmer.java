package main;
public class Farmer {
	
	private String name;
	private int age;
	private String skill;
	private double cropBonus;
	private int animalBonus;
	
//	public Farmer(String farmerName, int farmerAge, double cropBonus, int animalBonus) {
//		name = farmerName;
//		age = farmerAge;
//		this.cropBonus = cropBonus;
//		this.animalBonus = animalBonus;
//	}
	
	public Farmer(String skill, double cropBonus, int animalBonus) {
		this.skill = skill;
		this.cropBonus = cropBonus;
		this.animalBonus = animalBonus;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSkill() {
		return skill;
	}
	
	public double getCropBonus() {
		return cropBonus;
	}
	
	public int getAnimalBonus() {
		return animalBonus;
	}
}

	