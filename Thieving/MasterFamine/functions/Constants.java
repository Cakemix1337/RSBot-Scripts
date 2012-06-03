package masterFamine.functions;

import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;

public class Constants {
	/*
	 * NPCs
	 */
	public static int FarmerID = 2234;

	/*
	 * Areas
	 */
	public final static Tile[] marketArea = { new Tile(3069, 3264, 0),
			new Tile(3068, 3243, 0), new Tile(3076, 3239, 0),
			new Tile(3085, 3242, 0), new Tile(3086, 3248, 0),
			new Tile(3102, 3249, 0), new Tile(3096, 3260, 0),
			new Tile(3093, 3249, 0), new Tile(3084, 3248, 0),
			new Tile(3084, 3258, 0), new Tile(3077, 3258, 0),
			new Tile(3068, 3264, 0) },bankArea = { new Tile(3087, 3246, 0),
			new Tile(3087, 3240, 0), new Tile(3096, 3240, 0),
			new Tile(3096, 3248, 0), new Tile(3087, 3247, 0) };

	public final static Tile market = new Tile(3079, 3251, 0);
	public final static Tile bank = new Tile(3092, 3244, 0);

	/*
	 * Paths
	 */


	/*
	 * Items
	 */

	public static final int CheapSeedID[] = { 5319, 5307, 5305, 5322, 5099,
			5310, 5308, 5102, 5294, 5309, 5101, 5096, 5324, 5306, 5291, 5103,
			5292, 5097, 5281, 5098, 5105, 5106, 5280, 5297, 5311, 5104, 5293,
			5318, 5282, 5320, 1937 };
	List<int[]> dropSeedsIDs = Arrays.asList(Constants.CheapSeedID);
	public static final int GloveID = 10075;

	public static boolean stopScript = false;
	public static String status = "Starting";
	public static int FoodID = 0; // Set from GUI

	public static String[] foodNames = { "Trout", "Tuna", "Lobster", "Swordfish", "Monkfish",
			"Wine" };

	/*
	 * Booleans
	 */
	public static boolean Banking = false;
	public static boolean Eating = false;
	public static boolean Dropping = false;
	public static boolean Gloves = false;
	public static boolean Excalibur = false;
	public static boolean KillGuards = false;
	public static boolean prepareInv = true;
	public static boolean GlovesEquipped = false;
	public static boolean invPrepped = false; // Inventory prepared
	public static boolean Launch = true; // My logic sucks, this fixes it
	public static boolean AntiBanOn = true; // Debug purposes
	public static boolean GUIFinished = false;
	public static boolean NoFood = false;
	public static boolean Stunned = false;
	public static boolean UnderAttack = false;
	public static boolean SeedsDropped = false;
	public static boolean Dead = false;

	/*
	 * Statistics
	 */
	public static int SingleCount = 0; // Set the starting amount of successful
	public static int FailCount = 0; // Also set the starting amount of failed
	public static int DPCount = 0; // Double loot count
	public static int TripleCount = 0; // Triple loot count
	public static int QuadCount = 0; // Quadruple loot count
	public static int XPgain = 0; // Empty at the moment
	public static int StartXP = 0; // Starting xp at 0
	public static long StartTime = 0; // Starting time at 0
	public static Timer runTime = null;

	public static int HealPct = 10; // Set from GUI
	public static int foodEated = 0;
	public static int RandomInt;

	/*
	 * Setup
	 */

	public static Strategy setup;
	
	/*
	 * GUI
	 */

	public static JPanel contentPane;

	public static int gainedLvls = 0;

	public static long lastTime = 0;

}
