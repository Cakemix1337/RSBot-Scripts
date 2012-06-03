package masterFamine.functions.tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class AntiBan extends Strategy implements Task {
	/**
	 * Time to start the antiban?
	 */
	@Override
	public boolean validate() {
		return Constants.GUIFinished && Constants.AntiBanOn;
	}

	/**
	 * Start antiban
	 */
	@Override
	public void run() {
		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
		}

		Constants.RandomInt = Random.nextInt(1, 100);

		if (Constants.RandomInt <= 5) {
			Camera.setAngle(Random.nextInt(20, 300));
		}

		if (Constants.RandomInt > 95) {
			Camera.setPitch(Random.nextInt(0, 90));
		}

		Time.sleep(Random.nextInt(200, 600));
	}
}