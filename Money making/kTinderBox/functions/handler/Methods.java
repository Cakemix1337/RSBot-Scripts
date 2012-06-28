package kTinderBox.functions.handler;

import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;

public class Methods {

	public static void openTab(Tabs tab) {
		if (Tabs.getCurrent() != tab)
			tab.open();
	}

	public static boolean hasToRest() {
		return (Walking.getEnergy() < 20);
	}

}
