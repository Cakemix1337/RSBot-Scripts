package masterFamine.functions.Tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class Eat extends Strategy implements Task {

	/*
	 * Validate, I like it clean rather then 1 line.
	 */
	@Override
	public boolean validate() {
		if (Constants.stopScript)
			return Boolean.FALSE;

		if (!Constants.GUIFinished)
			return Boolean.FALSE;

		if (Constants.Eating && Inventory.getCount(Constants.FoodID) == 0)
			return Boolean.FALSE;

		if (Constants.Eating
				&& Players.getLocal().getHpPercent() < Constants.HealPct)
			return Boolean.TRUE;
		
		return Boolean.FALSE;
	}

	@Override
	public void run() {
		Constants.status = "Eating";
		if (Constants.Excalibur == true && Settings.get(300) / 10 == 100) {
			Tabs.ATTACK.open();
			Widgets.get(884, 4).click(true);
			Time.sleep(Random.nextInt(500, 1000));
			return;
		}

		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
		}

		for (final Item i : Inventory.getItems()) {
			if (i != null && i.getId() == Constants.FoodID) {
				i.getWidgetChild().interact("Eat");
				Time.sleep(Random.nextInt(400, 800));
				return;
			}
		}
	}
}