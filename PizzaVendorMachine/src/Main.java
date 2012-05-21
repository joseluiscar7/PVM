import rmit.utils.workflow.*;
import services.*;
import states.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PVMStateContext stateContext = new PVMStateContext();
		stateContext.setPizzaService(ServiceLocator.getPizzaService());
		
		InitState initState = new InitState();
		SelectPizzaState selectPizzaState = new SelectPizzaState();
		OrderPizzaState orderPizzaState = new OrderPizzaState();
		DeliverPizzaState deliverPizzaState = new DeliverPizzaState();
		AdminState adminState = new AdminState();
		
		Engine engine = new Engine();
		engine.from(initState).to(adminState).when(new Condition() {
			@Override
			public boolean check(StateContext data) {
				return ((PVMStateContext)data).isShowAdmin();
			}
			
		});
		engine.from(initState).to(selectPizzaState).to(orderPizzaState).to(selectPizzaState).when(new Condition() {
			@Override
			public boolean check(StateContext data) {
				PVMStateContext ctx = (PVMStateContext)data;
				return ctx.getPaidAmount() < ctx.getPrice();
			}
		});
		engine.from(orderPizzaState).to(deliverPizzaState).to(selectPizzaState);
		engine.from(initState).start(stateContext);
	}

}
