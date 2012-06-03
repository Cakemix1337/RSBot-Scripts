package masterFamine.functions.tasks;

import masterFamine.functions.Constants;
import masterFamine.functions.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class WalkToBank extends Strategy implements Task {
	/**
	 * Banking is enabled Inventory is full SceneObject is not BankArea
	 */
	public boolean validate() {
		return Constants.GUIFinished
				&& Constants.Launch
				&& Constants.Banking
				&& !Constants.BankArea.contains(Players.getLocal()
						.getLocation())
				|| Constants.Banking
				&& Inventory.getCount() == 28
				&& !Constants.BankArea.contains(Players.getLocal()
						.getLocation())
				|| Constants.Eating
				&& Constants.NoFood
				&& !Constants.BankArea.contains(Players.getLocal()
						.getLocation());
	}

	/**
	 * Walk to the correct place
	 */
	public void run() {
		Constants.Status = "Walking to bank";
		Walking.setRun(true);
		Methods.walkPath(Constants.toBank);
	}
}
