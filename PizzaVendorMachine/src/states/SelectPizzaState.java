package states;
import java.io.IOException;

import rmit.utils.workflow.State;

public class SelectPizzaState extends State {
	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Press Enter to confirm selection");
				
				try {
					while (System.in.read() != 13);
					exit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		

		
	}

}
