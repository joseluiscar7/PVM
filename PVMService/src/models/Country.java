package models;

public class Country {
	private int id;
	private String name;
	private String currencyName;
	
	public Country(int id, String name, String currencyName)
	{
		this.id = id;
		this.name = name;
		this.currencyName = currencyName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCurrencyName() {
		return currencyName;
	}
}
