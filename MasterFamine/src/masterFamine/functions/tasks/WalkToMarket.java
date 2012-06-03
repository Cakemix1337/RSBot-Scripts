package masterFamine.functions.tasks;

import masterFamine.functions.Constants;
import masterFamine.functions.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class WalkToMarket extends Strategy implements Task {
	/**
	 * Current SceneObject is not MarketArea Inventory is not full
	 */
	@Override
	public boolean validate() {
		return Constants.GUIFinished
				&& !Constants.Launch
				&& Constants.invPrepped
				&& Inventory.getCount() != 28
				&& !Constants.MarketArea.contains(Players.getLocal()
						.getLocation());
	}

	/**
	 * Enable run Walk to the market Reset booleans used for banking
	 */
	@Override
	public void run() {
		Constants.Status = "Walking to the market";
		Walking.setRun(true);
		Methods.walkPath(Constants.toMarket);
		Constants.prepareInv = false;
	}
}