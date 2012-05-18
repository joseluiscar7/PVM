package models;

public class Country {
	private int id;
	private String name;
	private String currencyName;
	private Coin[] coins;
	
	public Country(int id, String name, String currencyName, Coin[] coins)
	{
		this.id = id;
		this.name = name;
		this.currencyName = currencyName;
		this.coins = coins;
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
	
	public Coin[] getCoins()
	{
		return coins;
	}
}
