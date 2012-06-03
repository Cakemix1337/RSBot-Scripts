package masterFamine.functions.tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class StealSeeds extends Strategy implements Task {
	/**
	 * Validate if the following is true: Player is in the MarketArea Inventory
	 * not full If it is full, is banking turned off
	 */
	@Override
	public boolean validate() {
		return (Constants.GUIFinished
				&& !Constants.UnderAttack
				&& !Constants.Launch
				&& !Constants.NoFood
				&& Inventory.getCount() != 28
				&& Constants.MarketArea.contains(Players.getLocal()
						.getLocation())
				|| Constants.GUIFinished
				&& !Constants.Launch
				&& !Constants.UnderAttack
				&& !Constants.NoFood
				&& !Constants.Banking
				&& Constants.MarketArea.contains(Players.getLocal()
						.getLocation())
				|| Constants.GUIFinished
				&& !Constants.Launch
				&& !Constants.UnderAttack
				&& !Constants.NoFood
				&& !Constants.Eating
				&& !Constants.Banking
				&& Constants.MarketArea.contains(Players.getLocal()
						.getLocation()) && (!(Players.getLocal().getHpPercent() < 10) || !Constants.Dead));
	}

	/**
	 * This is what this class does Get the closest master farmer Interact with
	 * the closest master farmer
	 */
	@Override
	public void run() {
		Constants.Status = "Pickpocketting";
		NPC targetFarmer = NPCs.getNearest(2234);

		if (targetFarmer == null)
			return;

		if (Constants.Stunned) {
			targetFarmer.hover();
			Time.sleep(Random.nextInt(4000, 4500));
			Constants.Stunned = false;
		}

		if (Constants.Eating && Constants.FoodLeft == 0) {
			System.out.println("out of food");
			Constants.invPrepped = false;
			Constants.NoFood = true;
		}

		if (Players.getLocal().getInteracting() == null) {
			if (!targetFarmer.isOnScreen()) {
				Camera.turnTo(targetFarmer.getLocation());
				Time.sleep(Random.nextInt(150, 250));
				if (!targetFarmer.isOnScreen()) {
					Walking.walk(targetFarmer.getLocation());
					Time.sleep(Random.nextInt(350, 450));
				}
			}

			if (!Constants.Stunned) {
				targetFarmer.interact("Pickpocket");
				Time.sleep(Random.nextInt(350, 450));
			}
		}
	}

}
