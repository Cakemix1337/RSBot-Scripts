package kTinderBox.functions;

import kTinderBox.functions.handler.GrandExchangeHandler;

import org.powerbot.game.api.wrappers.Tile;

public class Constants {
	public static final int WIDGET_TINDERBOX = 26;
	public static final int WIDGET_SHOP = 1265;
	public static boolean REST = false;

	public static final int NPC_VULCAN = new Integer(4946);

	public static final int ITEM_TINDERBOX = new Integer(590);

	public static int TINDERBOX_BOUGHT = 0;
	public static int TINDERBOX_LEFT = 0;
	public static int TINDERBOX_PRICE = GrandExchangeHandler.accuratePrice(ITEM_TINDERBOX);

	public static boolean STOP_SCRIPT = false;
	public static boolean INVENTORY_FULL = false;

	public static final Tile TILE_BANK = new Tile(2725, 3492, 0);
	public static final Tile TILE_VULCAN = new Tile(2720, 3431, 0);

	public static final Tile[] TILES_BANK_TO_VULCAN = new Tile[] {
			new Tile(2725, 3492, 0),
			new Tile(2725, 3480, 0),
			new Tile(2725, 3465, 0),
			new Tile(2725, 3460, 0),
			new Tile(2725, 3455, 0),
			new Tile(2725, 3450, 0),
			new Tile(2725, 3445, 0),
			new Tile(2725, 3440, 0),
			new Tile(2725, 3435, 0),
			new Tile(2725, 3430, 0),
			new Tile(2725, 3421, 0) };

}
