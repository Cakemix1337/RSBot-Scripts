package kTinderBox;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.NumberFormat;
import java.util.Locale;

import kTinderBox.functions.Constants;
import kTinderBox.functions.handler.MessageHandler;
import kTinderBox.functions.handler.ProvideHandler;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

/**
 * @author Cakemix
 * 
 */
@Manifest(name = "kTinderBox", version = 0.22, description = "Time to bring those pesky summoning prices down, start in the camelot bank.", authors = { "Cakemix" })
public class Run extends ActiveScript implements PaintListener, MessageListener {

	@Override
	protected void setup() {
		log.info("Welcome to kTinerBox");
		log.info("Please report any bugs in the topic on the Powerbot forums not in a new thread.");

		for (Strategy strategy : ProvideHandler.strategies) {
			provide(strategy);
		}
	}

	@Override
	public void onStop() {
		// Environment.saveScreenCapture();
		System.out.println("Script ended.");
	}

	private Timer runTime = new Timer(0);


	private void drawString(Graphics g) {
		int Y = 40;

		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		String[] strings = {
				"Tinderboxes bought: ".concat(String
						.valueOf(nf.format(Constants.TINDERBOX_BOUGHT))
						.concat(" (P/H: ")
						.concat(String.valueOf(nf
								.format(Constants.TINDERBOX_BOUGHT
										* (3600000 / runTime.getElapsed()))))
						.concat(")")),
				"Tinderboxes left: ".concat(String.valueOf(nf
						.format(Constants.TINDERBOX_LEFT))),
				"Tinderbox price: ".concat(String.valueOf(nf
						.format(Constants.TINDERBOX_PRICE))),
				"Profit: "
						.concat(String
								.valueOf(
										nf.format((Constants.TINDERBOX_BOUGHT * Constants.TINDERBOX_PRICE)
												- Constants.TINDERBOX_BOUGHT))
								.concat(" (P/H: ")
								.concat(String.valueOf(nf
										.format(((Constants.TINDERBOX_BOUGHT * Constants.TINDERBOX_PRICE) - Constants.TINDERBOX_BOUGHT)
												* (3600000 / runTime
														.getElapsed())))))
						.concat(")"),
				"Profit left: "
						.concat(String.valueOf(nf
								.format((Constants.TINDERBOX_LEFT * Constants.TINDERBOX_PRICE)
										- Constants.TINDERBOX_LEFT))),
				"Run time: " + runTime.toElapsedString()};

		for (String s : strings) {
			g.drawString(s, 40, Y += (g.getFontMetrics().getHeight()));
		}
	}

	public void onRepaint(Graphics g) {
		drawMouse(g);
		g.setColor(Color.RED);
		drawString(g);
	}

	/**
	 * @author Deprecated
	 */
	private void drawMouse(final Graphics g) {
		g.setColor(Color.GREEN);
		g.drawOval(Mouse.getX() - 5, Mouse.getY() - 5, 11, 11);
		g.fillOval(Mouse.getX() - 2, Mouse.getY() - 2, 5, 5);
	}

	@Override
	public void messageReceived(MessageEvent e) {
		MessageHandler.messageReceived(e);
	}

}