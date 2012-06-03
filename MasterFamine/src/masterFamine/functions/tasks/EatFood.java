package masterFamine.functions.tasks;

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

public class EatFood extends Strategy implements Task {

	/**
	 * Eating is enabled HP is under limit set using GUI
	 */
	public boolean validate() {
		return Constants.GUIFinished == true && Constants.Launch == false
				&& Constants.Eating == true
				&& (Players.getLocal().getHpPercent() <= Constants.HealPct);
	}

	/**
	 * If excalibur is enable use that Else eat food
	 */
	public void run() {
		Constants.Status = "Healing";
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
				Constants.FoodLeft--;
				Time.sleep(Random.nextInt(200, 800));
				return;
			}

			if (Constants.FoodLeft == 0) {
				System.out.println("Out of food.");
				Constants.invPrepped = false;
				Constants.NoFood = true;
			}
		}
	}

}
