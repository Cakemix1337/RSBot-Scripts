package masterFamine.functions.tasks;

import masterFamine.functions.Constants;
import masterFamine.functions.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class KillGuard extends Strategy implements Task {
	/**
	 * Player is in combat
	 */
	public boolean validate() {
		return Constants.GUIFinished
				&& !Constants.Launch
				&& Constants.UnderAttack
				&& Players.getLocal().isInCombat()
				&& !Constants.BankArea.contains(Players.getLocal()
						.getLocation());
	}

	/**
	 * Run away if combat is disabled Kill the guard if combat is enabled
	 */
	public void run() {
		Constants.Status = "Stunned or in combat";

		if (Constants.KillGuards) {
			Constants.Status = "Waiting for the guard to die.";
			Time.sleep(Random.nextInt(5000, 15000));
		}

		Walking.setRun(true);
		Methods.walkPath(Constants.toBank);
		int sleep = Random.nextInt(5000, 15000);
		System.out.println("Got attacked, sleeping for " + (sleep / 1000)
				+ " seconds");
		Time.sleep(sleep);
		Constants.UnderAttack = false;
	}
}