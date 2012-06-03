package masterFamine.functions.tasks;

import masterFamine.functions.Constants;
import masterFamine.functions.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.Widget;

public class PrepareInventory extends Strategy implements Task {
	public boolean validate() {
		/**
		 * Player is in BankArea Inventory is empty
		 */
		return Constants.GUIFinished
				&& Constants.prepareInv
				&& Inventory.getCount() == 0
				&& Constants.BankArea
						.contains(Players.getLocal().getLocation())
				|| Constants.GUIFinished
				&& !Constants.prepareInv
				&& Constants.Banking
				&& Inventory.getCount() == 0
				&& Constants.BankArea
						.contains(Players.getLocal().getLocation())
				|| Constants.GUIFinished
				&& Constants.Eating
				&& Constants.NoFood
				&& Constants.BankArea
						.contains(Players.getLocal().getLocation());
	}

	public void run() {
		/**
		 * Open the bank Withdraw gloves when enabled Withdraw food when enabled
		 */
		Constants.Status = "Withdrawing items";
		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
		}
		final Widget BankScreen = Widgets.get(762);
		if (BankScreen.validate()) {
			if (Constants.Gloves) {
				Methods.withdraw(Constants.GloveID, 1);
				Time.sleep(Random.nextInt(1000, 2000));

			}
			if (Constants.Eating) {
				Methods.withdraw(Constants.FoodID, 5);
				Constants.SeedsDropped = false;
				Constants.FoodLeft = 5;
				Time.sleep(Random.nextInt(1000, 2000));
			}
			Constants.prepareInv = false;
			Constants.invPrepped = true;
			Constants.NoFood = false;
			Constants.Launch = false;
			Widgets.get(762, 45).click(true);
		} else {
			final SceneObject Booth = SceneEntities
					.getNearest(Constants.BankCounterID);
			if (Booth != null) {
				if (Booth.isOnScreen()) {
					if (Booth.interact("Bank"))
						Time.sleep(Random.nextInt(1000, 2000));
				}
			}
		}
	}
}
