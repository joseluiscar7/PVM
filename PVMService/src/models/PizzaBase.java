package models;

public class PizzaBase {
	private int id;
	private Country country;	
	private String name;
	private float price;
	private float tax;
	private int cookMinutes;
	
	public PizzaBase(int id, Country country, String name, float price,
			float tax, int cookMinutes) {
		super();
		this.id = id;
		this.country = country;
		this.name = name;
		this.price = price;
		this.tax = tax;
		this.cookMinutes = cookMinutes;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}	
	public float getTax() {
		return tax;
	}
	public void setTax(float tax)
	{
		this.tax = tax;
	}
}
