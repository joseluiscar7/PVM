package states;

import java.io.IOException;

import rmit.utils.workflow.State;

public class OrderPizzaState extends State {

	@Override
	public void start() {
		System.out.println("Press Enter to confirm order or 'C' to cancel the order");
		PVMStateData data = (PVMStateData)this.getStateData();
		try {
			int i;
			while((i = System.in.read()) != 13 && i != 67);
			if (i != 13)
				data.setCancelPayment(true);
			else
				data.setCancelPayment(false);
			
			this.exit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
