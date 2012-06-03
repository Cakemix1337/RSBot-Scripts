package masterFamine.functions;

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

import org.powerbot.concurrent.strategy.Strategy;

public class Strategies {
	// TODO: Check if you really need twice new classes.
	// @formatter:off
	public static Strategy antiBan 			= new Strategy(new AntiBan(),		   new AntiBan());
	public static Strategy stealSeeds 		= new Strategy(new StealSeeds(),   	   new StealSeeds());
	public static Strategy depositAll 		= new Strategy(new DepositAll(),   	   new DepositAll());
	public static Strategy dropSeeds 		= new Strategy(new DropSeeds(),        new DropSeeds());
	public static Strategy eatFood 			= new Strategy(new EatFood(),     	   new EatFood());
	public static Strategy equipGloves 		= new Strategy(new EquipGloves(),      new EquipGloves());
	public static Strategy killGuard 		= new Strategy(new KillGuard(),   	   new KillGuard());
	public static Strategy prepareInventory = new Strategy(new PrepareInventory(), new PrepareInventory());
	public static Strategy spinTicket 		= new Strategy(new SpinTicket(),   	   new SpinTicket());
	public static Strategy walkToBank 		= new Strategy(new WalkToBank(),   	   new WalkToBank());
	public static Strategy walkToMarket		= new Strategy(new WalkToMarket(),     new WalkToMarket());
	// @formatter:on
}
