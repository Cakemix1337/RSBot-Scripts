package kTinderBox.functions.strategies;

import kTinderBox.functions.Constants;
import kTinderBox.functions.handler.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class Rest extends Strategy implements Task {

	@Override
	public void run() {
		if (Players.getLocal().getAnimation() == -1) {
			if (Widgets.get(750, 2).interact("Rest"))
				while (90 > Walking.getEnergy()) {
					if (Players.getLocal().getAnimation() == -1)
						Widgets.get(750, 2).interact("Rest");
					Time.sleep(500);
				}

			if (!Walking.isRunEnabled()) {
				Walking.setRun(true);
			}
			restTo = Random.nextInt(60, 97);
		}
	}

	@Override
	public boolean validate() {
		if (Constants.STOP_SCRIPT)
			return false;

		return Methods.hasToRest();
	}

	public int restTo = Random.nextInt(60, 97);
}
