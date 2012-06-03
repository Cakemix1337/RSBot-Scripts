package masterFamine.functions.Tasks;

import java.util.Arrays;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class Drop extends Strategy implements Task {

	/*
	 * Validate, I like it clean rather then 1 line.
	 */
	@Override
	public boolean validate() {
		if (Constants.stopScript)
			return Boolean.FALSE;

		if (!Constants.GUIFinished)
			return Boolean.FALSE;

		if (Constants.Dropping && Inventory.getCount() == 28)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	@Override
	public void run() {
		Constants.status = "Dropping seeds";

		if (!Tabs.INVENTORY.open()) {
			Tabs.INVENTORY.open();
		}
		Inventory.getItems(new Filter<Item>() {
			@Override
			public boolean accept(Item item) {
				if (Arrays.asList(Constants.CheapSeedID).contains(item.getId())) {
					
					return true;
				}
				return false;
			}
		});
		for (Item i : Inventory.getItems()) {
			for (int id : Constants.CheapSeedID) {
				if (i.getId() == id) {
					i.getWidgetChild().interact("drop");
					Time.sleep(Random.nextInt(100, 200));
					break;
				}
			}
		}

	}
}