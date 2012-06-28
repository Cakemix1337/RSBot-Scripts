package masterFamine.functions.Tasks;

import java.util.Date;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class AntiBan extends Strategy implements Task {
	/**
	 * Time to start the antiban?
	 */
	@Override
	public boolean validate() {
		return Constants.GUIFinished && Constants.AntiBanOn
				&& !Constants.stopScript;
	}

	/**
	 * Start antiban
	 */
	@Override
	public void run() {
		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
		}
		
		if ((new Date().getTime() / 1000 - Constants.lastTime) >= 5) { //Do it if it has gone 5 sec to not spam it.
			Inventory.getItems(new Filter<Item>() {
				@Override
				public boolean accept(Item item) {
					if (item.getName().toLowerCase().equals("spin ticket")) {
						item.getWidgetChild().interact("Claim");
						Time.sleep(Random.nextInt(200, 800));
						return true;
					}
					return false;
				}
			});
			Constants.lastTime = new Date().getTime() / 1000;
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