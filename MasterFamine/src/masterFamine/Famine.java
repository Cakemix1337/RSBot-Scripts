package masterFamine;

import java.awt.Graphics;

import masterFamine.functions.Constants;
import masterFamine.functions.GUI;
import masterFamine.functions.handlers.MessageHandler;
import masterFamine.functions.handlers.PaintHandler;
import masterFamine.functions.tasks.AntiBan;
import masterFamine.functions.tasks.DepositAll;
import masterFamine.functions.tasks.DropSeeds;
import masterFamine.functions.tasks.EatFood;
import masterFamine.functions.tasks.EquipGloves;
import masterFamine.functions.tasks.KillGuard;
import masterFamine.functions.tasks.PrepareInventory;
import masterFamine.functions.tasks.SpinTicket;
import masterFamine.functions.tasks.StealSeeds;
import masterFamine.functions.tasks.WalkToBank;
import masterFamine.functions.tasks.WalkToMarket;

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

@Manifest(authors = { "Nalar & Cakemix" }, name = "MasterFamine v2", version = 2.01, description = "Info is in the GUI.")
public class Famine extends ActiveScript implements PaintListener,
		MessageListener, Condition, Task {

	@Override
	protected void setup() {
		// Give some output to the RSBot log
		log.info("Welcome to MasterFamine v2");
		log.info("Please report any bugs in the topic on the Powerbot forums");
		log.info("Thread: ");
		// Get some of the starting parameters
		Constants.StartXP = Skills.getExperience(Skills.THIEVING);
		// Load the necessary pictures for the paint
		// Paint pictures are not implemented yet ;-)
		Constants.setup = new Strategy(this, this);
		Constants.setup.setReset(true);
		// Provide all the neccesary tasks

		provide(Constants.setup);

		provide(new StealSeeds()); 
		provide(new WalkToBank()); 
		provide(new DepositAll()); 
		provide(new PrepareInventory());
		provide(new WalkToMarket()); 
		provide(new KillGuard()); 
		provide(new EatFood()); 
		provide(new DropSeeds());
		provide(new EquipGloves());
		provide(new SpinTicket()); 

		Strategy anti = new Strategy(new AntiBan(), new AntiBan());
		anti.setLock(false);
		provide(anti);
	}

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
