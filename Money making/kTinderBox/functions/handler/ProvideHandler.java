package kTinderBox.functions.handler;

import java.util.ArrayList;

import kTinderBox.functions.strategies.AntiBan;
import kTinderBox.functions.strategies.Banking;
import kTinderBox.functions.strategies.Buy;
import kTinderBox.functions.strategies.Rest;
import kTinderBox.functions.strategies.Vulcan;

import org.powerbot.concurrent.strategy.Strategy;

/**
 * @author Cakemix
 */
public class ProvideHandler {

	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>() {
		{

			add(new Vulcan());
			add(new Banking());
			add(new Buy());
			add(new Rest());
			add(new AntiBan());
		}

		private static final long serialVersionUID = 1L;
	};
}
