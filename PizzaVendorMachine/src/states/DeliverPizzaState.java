package states;

import java.io.IOException;

import rmit.utils.workflow.State;

public class DeliverPizzaState extends State {

	@Override
	public void start() {
		System.out.println("Press Enter to confirm delivery");
		
		try {
			while (System.in.read() != 13);
			this.exit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
