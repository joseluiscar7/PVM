import rmit.utils.workflow.*;
import services.*;
import states.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PizzaService pizzaService = ServiceLocator.getPizzaService();
		
		PVMStateContext stateContext = new PVMStateContext();
		
		SelectPizzaState selectPizzaState = new SelectPizzaState(pizzaService);
		OrderPizzaState orderPizzaState = new OrderPizzaState();
		DeliverPizzaState deliverPizzaState = new DeliverPizzaState();
		
		Engine engine = new Engine();
		engine.from(selectPizzaState).to(orderPizzaState).to(selectPizzaState).when(new Condition() {
			@Override
			public boolean check(StateContext data) {
				return ((PVMStateContext)data).isCancelPayment();
			}
		});
		engine.from(orderPizzaState).to(deliverPizzaState).to(selectPizzaState);
		engine.from(selectPizzaState).start(stateContext);
	}

}
