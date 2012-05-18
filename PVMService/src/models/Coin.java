package models;

public class Coin {
	private float value;
	private String name;
	
	public Coin(String name, float value) {
		this.setValue(value);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public float getValue() {
		return value;
	}

	private void setValue(float value) {
		this.value = value;
	}
}
