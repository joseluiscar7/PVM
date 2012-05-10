package models;

public class PizzaTopping {
	private int id;
	private Country country;	
	private String name;
	private float price;
	private float tax;
	
	public PizzaTopping(int id, Country country, String name, float price,
			float tax) {
		super();
		this.id = id;
		this.country = country;
		this.name = name;
		this.price = price;
		this.tax = tax;
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
