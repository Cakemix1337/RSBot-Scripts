package masterFamine.functions.tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class DropSeeds extends Strategy implements Task {
	/**
	 * Inventory is full Dropping is enabled
	 */
	public boolean validate() {
		return Constants.GUIFinished && !Constants.Launch && Constants.Dropping
				&& Inventory.getCount() == 28 || Constants.GUIFinished
				&& !Constants.Launch && Constants.Dropping && Constants.NoFood
				&& !Constants.SeedsDropped;
	}

	/**
	 * Drop all seeds in CheapSeedID
	 */
	public void run() {
		Constants.Status = "Dropping seeds";

		if (!Tabs.INVENTORY.open()) {
			Tabs.INVENTORY.open();
		}

		for (Item i : Inventory.getItems()) {
			for (int id : Constants.CheapSeedID) {
				if (i.getId() == id) {
					i.getWidgetChild().interact("drop");
					Time.sleep(Random.nextInt(100, 200));
					break;
				}
			}
		}

		Constants.SeedsDropped = true;
	}
}
