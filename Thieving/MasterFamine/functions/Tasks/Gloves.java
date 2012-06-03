package masterFamine.functions.Tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class Gloves extends Strategy implements Task {
	/**
	 * Are gloves enabled Are gloves equipped
	 */
	public boolean validate() {
		return Constants.GUIFinished && !Constants.stopScript && Constants.invPrepped
				&& Constants.Gloves
				&& !Constants.GlovesEquipped;
	}

	/**
	 * Open inventory Equip gloves set equipped bool to true
	 */
	public void run() {
		Constants.status = "Equipping gloves of silence";
		
		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
		}
		
		for (final Item i : Inventory.getItems()) {
			if (i != null && i.getId() == Constants.GloveID) {
				i.getWidgetChild().interact("Wear");
				Constants.GlovesEquipped = true;
				Time.sleep(Random.nextInt(200, 800));
				return;
			}
		}
	}
}
