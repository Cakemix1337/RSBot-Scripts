package masterFamine.functions.tasks;

import masterFamine.functions.Constants;

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
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class DepositAll extends Strategy implements Task {
	public boolean validate() {
		/**
		 * check if the player is in the bank area
		 */
		return Constants.GUIFinished
				&& Constants.Launch
				&& Constants.Banking
				|| Constants.GUIFinished
				&& Constants.Banking
				&& Inventory.getCount() == 28
				&& Constants.BankArea
						.contains(Players.getLocal().getLocation())
				|| Constants.GUIFinished
				&& Constants.NoFood
				&& Constants.BankArea
						.contains(Players.getLocal().getLocation());
	}

	public void run() {
		/**
		 * Open the bank Deposit all items
		 */
		Constants.Status = "Depositing items.";
		if (Tabs.getCurrent() != Tabs.INVENTORY) {
			Tabs.INVENTORY.open();
		}
		final Widget BankScreen = Widgets.get(762);
		final WidgetChild depositButton = Widgets.get(762, 34);
		if (BankScreen.validate()) {
			if (depositButton.validate()) {
				if (depositButton.click(true)) {
					Constants.Launch = false;
					Time.sleep(Random.nextInt(1000, 2000));
				}
			}
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
