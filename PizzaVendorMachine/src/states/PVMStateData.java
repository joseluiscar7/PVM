package states;

import rmit.utils.workflow.StateData;

public class PVMStateData implements StateData {
	private boolean cancelPayment = false;

	public boolean isCancelPayment() {
		return cancelPayment;
	}

	public void setCancelPayment(boolean cancelPayment) {
		this.cancelPayment = cancelPayment;
	}
	
}
