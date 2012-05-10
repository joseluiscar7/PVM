package models;

public class PizzaType {
	private int id;
	private Country country;	
	private String name;
	
	public PizzaType(int id, Country country, String name) {
		super();
		this.id = id;
		this.country = country;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public Country getCountry() {
		return country;
	}

	public String getName() {
		return name;
	}
	
}
