package models;

import java.util.List;

public class Vendor {
	private int id;
	private Country country;
	private String address;
	private List<BaseStock> baseStockList;
	private List<ToppingStock> toppingStockList;
	
	public Vendor(int id, Country country, String address,
			List<BaseStock> baseStockList, List<ToppingStock> toppingStockList) {
		super();
		this.id = id;
		this.country = country;
		this.address = address;
		this.baseStockList = baseStockList;
		this.toppingStockList = toppingStockList;
	}

	public int getId() {
		return id;
	}

	public Country getCountry() {
		return country;
	}

	public String getAddress() {
		return address;
	}

	public List<BaseStock> getBaseStockList() {
		return baseStockList;
	}

	public List<ToppingStock> getToppingStockList() {
		return toppingStockList;
	}
}
