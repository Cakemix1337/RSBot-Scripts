package masterFamine.functions.Tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

/**
 * Fleeing task
 * @author Nalar
 * @author Cakemix
 *
 */
public class Flee extends Strategy implements Task {

	/*
	 * Validate, I like it clean rather then 1 line.
	 */
	@Override
	public boolean validate() {
		if (!Constants.GUIFinished)
			return Boolean.FALSE;

		if (Constants.UnderAttack)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	public final Tile[] marketArea = { new Tile(3069, 3264, 0),
			new Tile(3068, 3243, 0), new Tile(3076, 3239, 0),
			new Tile(3085, 3242, 0), new Tile(3086, 3248, 0),
			new Tile(3102, 3249, 0), new Tile(3096, 3260, 0),
			new Tile(3093, 3249, 0), new Tile(3084, 3248, 0),
			new Tile(3084, 3258, 0), new Tile(3077, 3258, 0),
			new Tile(3068, 3264, 0) };

	public final Tile[] bankArea = { new Tile(3087, 3246, 0),
			new Tile(3087, 3240, 0), new Tile(3096, 3240, 0),
			new Tile(3096, 3248, 0), new Tile(3087, 3247, 0) };

	public final Tile market = new Tile(3079, 3251, 0);
	public final Tile bank = new Tile(3092, 3244, 0);

	@Override
	public void run() {Constants.status = "Fleeing";
		if (!new Area(bankArea).contains(Players.getLocal().getLocation())) {
			if (!Walking.isRunEnabled())
				Walking.setRun(true);

			Walking.findPath(bank).traverse();
			Time.sleep(250);
		} else {
			int sleep = Random.nextInt(1000, 2000);
			System.out.println("Got attacked, sleeping for " + (sleep / 1000)
					+ " seconds");
			Time.sleep(sleep);
			Walking.findPath(market).traverse();
			if (!Players.getLocal().isInCombat())
				Constants.UnderAttack = false;
		}
	}
}