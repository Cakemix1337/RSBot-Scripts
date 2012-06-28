package masterFamine.functions.Tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;

/**
 * Banks all items and if eating is enabled withdraws food.
 * 
 * @author Cakemix
 * @since 2.07
 */
public class Banking extends Strategy implements Task {

	/*
	 * Validate, I like it clean rather then 1 line.
	 */
	@Override
	public boolean validate() {
		if (Constants.stopScript)
			return Boolean.FALSE;

		if (!Constants.GUIFinished)
			return Boolean.FALSE;

		if (!Constants.Banking)
			return Boolean.FALSE;

		if (Bank.isOpen())
			return Boolean.TRUE;

		if (Constants.Eating && Inventory.getCount(Constants.FoodID) == 0)
			return Boolean.TRUE;

		if (!Constants.Dropping && Inventory.getCount() == 28 && Inventory.getCount(Constants.FoodID) == 0)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}


	@Override
	public void run() {
		Constants.status = "Banking";
		if (!Bank.isOpen()) {
			if (!new Area(Constants.bankArea).contains(Players.getLocal().getLocation())) {
				Walking.findPath(Constants.bank).traverse();
			} else {
				Bank.open();
				Time.sleep(50, 120);
			}
		} else {
			if (Inventory.getCount(Constants.FoodID) == 0
					&& Inventory.getCount() != 0) {
				Bank.depositInventory();
				Time.sleep(150, 200);
			}

			if (Constants.Eating && Inventory.getCount(Constants.FoodID) == 0) {
				if (Bank.getItemCount(Constants.FoodID) == 0) {
					Constants.stopScript = Boolean.TRUE;
					System.out.println("No food in the bank, stopping the script.");
				} else {
					Bank.withdraw(Constants.FoodID, 5);
					Time.sleep(250, 140);
				}
			} else {
				Bank.close();
			}

		}
	}
}
