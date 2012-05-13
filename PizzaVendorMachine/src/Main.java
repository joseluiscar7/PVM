import rmit.utils.workflow.*;
import states.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PVMStateData stateData = new PVMStateData();
		
		SelectPizzaState selectPizzaState = new SelectPizzaState();
		OrderPizzaState orderPizzaState = new OrderPizzaState();
		DeliverPizzaState deliverPizzaState = new DeliverPizzaState();
		
		Engine engine = new Engine();
		engine.from(selectPizzaState).to(orderPizzaState).to(selectPizzaState).when(new Condition() {
			@Override
			public boolean check(StateData data) {
				return ((PVMStateData)data).isCancelPayment();
			}
		});
		engine.from(orderPizzaState).to(deliverPizzaState).to(selectPizzaState);
		engine.from(selectPizzaState).start(stateData);
	}

}
