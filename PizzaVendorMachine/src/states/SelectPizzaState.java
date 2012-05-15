package states;
import java.io.IOException;

import rmit.utils.workflow.State;
import thinlet.*;

public class SelectPizzaState extends State {
	@Override
	public void start() {
		Thinlet thinlet = new Thinlet();
		try {
			thinlet.add(thinlet.parse("/UIDefinition/SelectPizza.xml"));
			new FrameLauncher("Select Pizza", thinlet, 320, 240);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
