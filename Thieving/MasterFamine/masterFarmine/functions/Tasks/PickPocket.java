package masterFamine.functions.Tasks;

import masterFamine.functions.Constants;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Stealing task
 * 
 * @author Cakemix
 * 
 */
public class PickPocket extends Strategy implements Task {

	/*
	 * Validate, I like it clean rather then 1 line.
	 */
	@Override
	public boolean validate() {
		if (Constants.stopScript)
			return Boolean.FALSE;

		if (!Constants.GUIFinished)
			return Boolean.FALSE;

		if (Constants.UnderAttack)
			return Boolean.FALSE;

		if (Constants.Dropping && Inventory.getCount() == 28)
			return Boolean.FALSE;

		if (Constants.Eating && Inventory.getCount(Constants.FoodID) == 0)
			return Boolean.FALSE;

		if (Players.getLocal().getHpPercent() < Constants.HealPct)
			return Boolean.FALSE;
		
		return Boolean.TRUE;
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
	public void run() {
		Constants.status = "Pickpocketing";
		NPC npcFarmer = NPCs.getNearest(2234);

		if (npcFarmer == null)
			return;

		if (!new Area(marketArea).contains(Players.getLocal().getLocation()) && Calculations.distance(npcFarmer,Players.getLocal().getLocation()) >= 8) {
			Walking.findPath(market).traverse();
			Time.sleep(120, 200);
		} else {
			if (Constants.Stunned) {
				npcFarmer.hover();
				Time.sleep(Random.nextInt(4000, 4500));
				Constants.Stunned = false;
			}

			if (Players.getLocal().getInteracting() == null) {
				if (!npcFarmer.isOnScreen()) {
					Camera.turnTo(npcFarmer.getLocation());
					Time.sleep(Random.nextInt(150, 250));
					if (!npcFarmer.isOnScreen()) {
						Walking.findPath(npcFarmer.getLocation()).traverse();
						Time.sleep(Random.nextInt(350, 450));
					}
				} else {
					npcFarmer.interact("Pickpocket");
					Time.sleep(Random.nextInt(350, 450));
				}
			}
		}

	}
}