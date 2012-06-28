package kTinderBox.functions.handler;

import kTinderBox.functions.Constants;

import org.powerbot.game.bot.event.MessageEvent;

public class MessageHandler {

	public static void messageReceived(MessageEvent e) {
		if (e.getMessage().contains("You have no")) {
			Constants.INVENTORY_FULL = true;
		} else if (e.getMessage().contains("coins have been")) {
			Constants.TINDERBOX_BOUGHT += Integer.parseInt(e.getMessage()
					.substring(0, e.getMessage().indexOf(" ")));
		}
	}
}
