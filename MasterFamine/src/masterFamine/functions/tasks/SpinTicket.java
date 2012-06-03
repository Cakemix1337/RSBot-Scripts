package masterFamine.functions.tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class SpinTicket extends Strategy implements Task {
	/**
	 * Are gloves enabled Are gloves equipped
	 */
	public boolean validate() {
		return Constants.GUIFinished
				&& Constants.BankArea
						.contains(Players.getLocal().getLocation())
				&& Inventory.getCount() == 1;
	}

	/**
	 * Claim spin tickets
	 */
	public void run() {
		Constants.Status = "Claiming spin";

		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
		}

		for (final Item i : Inventory.getItems()) {
			if (i != null && i.getName().equalsIgnoreCase("spin ticket")) {
				i.getWidgetChild().interact("Claim");
				Time.sleep(Random.nextInt(200, 800));
				return;
			}
		}
	}
}