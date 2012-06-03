package masterFamine.functions.handlers;

import masterFamine.functions.Constants;

import org.powerbot.game.api.util.Time;
import org.powerbot.game.bot.event.MessageEvent;

public class MessageHandler {

	public static void messageReceived(MessageEvent e) {
		if (e.getMessage().contains("You pick the master farmer")) {
			Constants.SingleCount++;
			Constants.XPgain = Constants.XPgain + 43;
		} else if (e.getMessage().contains("You fail")) {
			Constants.FailCount++;
		} else if (e.getMessage().contains("double the loot")) {
			Constants.DPCount++;
		} else if (e.getMessage().contains("triple the loot")) {
			Constants.TripleCount++;
		} else if (e.getMessage().contains("quadruple the loot")) {
			Constants.QuadCount++;
		} else if (e.getMessage().contains("Your gloves")) {
			Constants.GlovesEquipped = false;
		} else if (e.getMessage().contains("been stunned")) {
			Constants.Stunned = true;
		} else if (e.getMessage().contains("that during combat")) {
			Constants.UnderAttack = true;
		} else if (e.getMessage().contains("Oh dear, you are dead!")) {
			Constants.Dead = true;
		} else if (e.getMessage().contains("You eat")) {
			Constants.foodEated++;
		} else if (e.getMessage().contains("You've just advanced")) {
			System.out.println(e.getMessage());
			Time.sleep(1500);
			Constants.gainedLvls++;
		}
	}
}
