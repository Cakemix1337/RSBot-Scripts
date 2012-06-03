package masterFamine.functions;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Methods {
	/**
	 * @author Nalar
	 * @author Cakemix
	 */

	/**
	 * The following banking method was taken from iTPotionMixer and slightly
	 * modified to withdraw one or five instead of 14 ;-) modifided by Cakemix
	 * to be only one method and not two.
	 * 
	 * @param item
	 *            Item id that the bot will look for
	 * @param amount
	 *            Amount as in 1 or 5.
	 */
	public static boolean withdraw(int item, int amount) {
		String amountString = null;

		switch (amount) { // Lazy switch.
		case 1:
			amountString = "Withdraw-1";
			break;
		case 5:
			amountString = "Withdraw-5";
			break;
		default:
			System.out.println("Using an illegal amount, switching it to 1.");
			amountString = "Withdraw-1"; // Failbreak.
			break;
		}

		Widget bank = Widgets.get(762);
		WidgetChild items = bank.getChild(95);

		int itemX = items.getAbsoluteX();
		int itemY = items.getAbsoluteY();

		for (WidgetChild child : items.getChildren()) {
			if (child.getChildId() == item) {
				Mouse.click(child.getRelativeX() + itemX
						+ (child.getWidth() / 2), child.getRelativeY() + itemY
						+ (child.getHeight() / 2), false);
				return Menu.select(amountString);
			}
		}

		return false;
	}

	private static Tile getNext(final Tile[] tiles) {
		for (int i = tiles.length - 1; i >= 0; --i) {
			if (Calculations.distance(Players.getLocal().getLocation(),
					tiles[i]) < 15) {
				return tiles[i];
			}
		}
		return null;
	}

	private static boolean dividePath(final Tile t) {
		final Tile mine = Players.getLocal().getLocation();
		final int x = t.getX(), y = t.getY(), z = t.getPlane(), myX = mine
				.getX(), myY = mine.getY();
		final Tile newT = new Tile((int) (x + myX) / 2, (int) (y + myY) / 2, z);
		if (Walking.walk(newT)) {
			return true;
		}
		return dividePath(newT);
	}

	/**
	 * The following walkPath method was taken from ProPowerChopper Credits go
	 * to Snotboy808
	 */

	public static boolean walkPath(final Tile[] path) {
		boolean a = false;
		final Tile start = path[0];
		final Tile next = getNext(path);
		final Tile dest = Walking.getDestination();
		final Tile myTile = Players.getLocal().getLocation();
		if (dest.getX() == -1 || Calculations.distance(myTile, dest) < 6
				|| Calculations.distance(next, Walking.getDestination()) > 3) {

			if (!Walking.walk(next)) {
				if (Walking.walk(start)) {
					Time.sleep(500);
					a = true;
				} else {
					if (dividePath(start))
						Time.sleep(500);

				}
			} else {
				Time.sleep(500);
				a = true;
			}
		}
		return a;
	}
}
