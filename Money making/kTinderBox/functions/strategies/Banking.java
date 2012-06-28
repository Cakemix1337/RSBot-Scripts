package kTinderBox.functions.strategies;

import kTinderBox.functions.Constants;
import kTinderBox.functions.handler.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.map.TilePath;

public class Banking extends Strategy implements Task {

	public boolean validate() {
		if (Constants.STOP_SCRIPT)
			return false;

		Methods.openTab(Tabs.INVENTORY);

		if (Inventory.isFull() && !Methods.hasToRest())
			return true;

		return false;
	}

	public void run() {
		if (Calculations.distanceTo(Constants.TILE_BANK) > 7) {
			TilePath path = Walking.newTilePath(Constants.TILES_BANK_TO_VULCAN).reverse();
			
			if (path.getNext().distanceTo() < 7)
				path.traverse();
			
			Time.sleep(200);
		} else {
			if (Bank.isOpen()) {
				if (Inventory.getCount(Constants.ITEM_TINDERBOX) != 0) {
					if (Bank.depositInventory() && Bank.close())
						Time.sleep(100, 300);
				} else {
					if (Bank.close())
						Time.sleep(100, 300);
				}
			} else {
				if (Bank.open())
					Time.sleep(100, 300);
			}
		}
	}
}
