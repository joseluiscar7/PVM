package states;

import java.io.IOException;

import rmit.utils.workflow.State;

public class OrderPizzaState extends State {

	@Override
	public void start() {
		System.out.println("Press Enter to confirm order or 'C' to cancel the order");
		PVMStateContext data = (PVMStateContext)this.getStateContext();
		try {
			int i;
			while((i = System.in.read()) != 13 && i != 67);
			if (i != 13)
				data.setCancelPayment(true);
			else
				data.setCancelPayment(false);
			
			this.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public PVMStateContext getStateContext()
	{
		return (PVMStateContext)super.getStateContext();
	}
}
