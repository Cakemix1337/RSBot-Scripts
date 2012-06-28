package kTinderBox.functions.strategies;

import kTinderBox.functions.handler.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class AntiBan extends Strategy implements Task {

	public AntiBan() {
		setLock(false);
	}

	public boolean validate() {
		return Random.nextInt(0, 50) == 31;
	}

	public void run() {
		final int num = Random.nextInt(0, 24);

		switch (num) {
		case 11:
			Tabs tab = Tabs.getCurrent();
			Methods.openTab(Tabs.STATS);
			Skills.getWidgetChild(Skills.WIDGET_CONSTITUTION).hover();
			Time.sleep(400, 600);
			Methods.openTab(tab);
			break;
		case 22:
			for (int i = 0; i < 5; i++) {
				Mouse.move(Random.nextInt(1, 450), Random.nextInt(20, 710));
			}
			break;
		}
	}
}
