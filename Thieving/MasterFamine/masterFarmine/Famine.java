package masterFamine;

import java.awt.Graphics;

import masterFamine.functions.Constants;
import masterFamine.functions.GUI;
import masterFamine.functions.Tasks.AntiBan;
import masterFamine.functions.Tasks.Banking;
import masterFamine.functions.Tasks.Drop;
import masterFamine.functions.Tasks.Eat;
import masterFamine.functions.Tasks.Flee;
import masterFamine.functions.Tasks.Gloves;
import masterFamine.functions.Tasks.PickPocket;
import masterFamine.functions.handlers.MessageHandler;
import masterFamine.functions.handlers.PaintHandler;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(authors = { "Nalar & Cakemix" }, name = "MasterFamine v2", version = 2.14, description = "Does masterfarmer for you, start in the draynor market or bank.")
public class Famine extends ActiveScript implements PaintListener,
		MessageListener, Condition, Task {

	@Override
	protected void setup() {
		log.info("Welcome to MasterFamine v2");
		log.info("Please report any bugs in the topic on the Powerbot forums");
		log.info("Thread: http://bit.ly/MasterFamine");

		Constants.StartXP = Skills.getExperience(Skills.THIEVING);
		Constants.setup = new Strategy(this, this);
		Constants.setup.setReset(true);

		provide(Constants.setup); 
		
		provide(new PickPocket());
		provide(new Banking());
		provide(new Flee());
		provide(new Eat());
		provide(new Gloves());
		provide(new Drop());
		provide(new AntiBan());
		
		/*
		 * provide(Strategies.stealSeeds);
		 * 
		 * provide(Strategies.walkToBank);
		 * 
		 * provide(Strategies.depositAll);
		 * 
		 * provide(Strategies.prepareInventory);
		 * 
		 * provide(Strategies.walkToMarket);
		 * 
		 * provide(Strategies.killGuard);
		 * 
		 * provide(Strategies.eatFood);
		 * 
		 * provide(Strategies.dropSeeds);
		 * 
		 * provide(Strategies.equipGloves);
		 * 
		 * provide(Strategies.spinTicket);
		 * 
		 * Strategy anti = new Strategy(new AntiBan(), new AntiBan());
		 * anti.setLock(false); provide(anti);
		 */}

	@Override
	public void run() {
		final GUI gui = new GUI();
		gui.setVisible(true);

		while (isRunning() && gui.isRunning() && gui.isVisible())
			Time.sleep(100);

		if (gui.isRunning()) {
			gui.dispose();
			stop();
		}

		revoke(Constants.setup);
	}

	@Override
	public boolean validate() {
		return Game.isLoggedIn() && Players.getLocal() != null
				&& Players.getLocal().isOnScreen();
	}

	@Override
	public void messageReceived(MessageEvent e) {
		MessageHandler.messageReceived(e);
	}

	@Override
	public void onRepaint(Graphics e) {
		PaintHandler.onRepaint(e);
	}

}
