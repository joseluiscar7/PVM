package rmit.mvvm;

import java.util.Observable;

class EventObservable extends Observable{

	public void invoke(Object[] args) {
		this.setChanged();
		this.notifyObservers(args);
	}

}
