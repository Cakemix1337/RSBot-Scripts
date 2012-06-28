package kTinderBox.functions.strategies;

import kTinderBox.functions.Constants;
import kTinderBox.functions.handler.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.map.TilePath;

public class Vulcan extends Strategy implements Task {

	public boolean validate() {
		if (Constants.STOP_SCRIPT)
			return false;

		Methods.openTab(Tabs.INVENTORY);

		if (!Inventory.isFull()
				&& !Widgets.get(Constants.WIDGET_SHOP).validate()
				&& !Methods.hasToRest())
			return true;

		return false;
	}

	public void run() {
		NPC vulcan = NPCs.getNearest(Constants.NPC_VULCAN);
		if (Calculations.distanceTo(Constants.TILE_VULCAN) > 10
				|| vulcan == null) {
			TilePath path = Walking.newTilePath(Constants.TILES_BANK_TO_VULCAN);

				path.traverse();

			Time.sleep(200);
		} else {
			if (!vulcan.isOnScreen() && !Players.getLocal().isMoving()) {
				Walking.walk(vulcan.getLocation());
				Time.sleep(200, 400);
				return;
			}
			
			if (!Widgets.get(Constants.WIDGET_SHOP)
					.getChild(Constants.WIDGET_TINDERBOX).validate()
					&& vulcan.interact("Trade"))
				Time.sleep(700, 1200);

		}
	}
}
