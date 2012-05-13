package rmit.mvvm;

import java.util.Observable;

class ValueObservable extends Observable
{
	private Object value;

	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
		this.setChanged();
		this.notifyObservers();
	}
}