package masterFamine.functions.handlers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import masterFamine.functions.Constants;

import org.powerbot.game.api.methods.tab.Skills;

public class PaintHandler {

	public static void onRepaint(Graphics g) {
		long runTimer = System.currentTimeMillis() - Constants.StartTime;
		int XPGained = Skills.getExperience(Skills.THIEVING)
				- Constants.StartXP;
		int XPHour = (int) ((XPGained) * 3600000D / runTimer);
		int XPNext = (Skills.getExperienceRequired(Skills
				.getLevel(Skills.THIEVING) + 1));
		int XPCurr = (Skills.getExperience(Skills.THIEVING));
		int XPLeft = XPNext - XPCurr;
		g.drawString("Constants.status: " + Constants.status, 10, 50);

		Color Color1 = new Color(0, 0, 0, 150);
		g.setColor(Color1);
		g.fillRect(370, 180, 130, 155);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", 12, 12));
		g.drawString("Single loot: " + Constants.SingleCount, 380, 200);
		g.drawString("Double loot: " + Constants.DPCount, 380, 215);
		g.drawString("Triple loot: " + Constants.TripleCount, 380, 230);
		g.drawString("Quad loot: " + Constants.QuadCount, 380, 245);
		g.drawString("Fails: " + Constants.FailCount, 380, 260);
		g.drawString("XP Gain: " + XPGained, 380, 275);
		g.drawString("XP/Hour: " + XPHour, 380, 290);
		g.drawString("XP to lvl: " + XPLeft, 380, 305);
		g.drawString(
				"Runtime: "
						+ (Constants.runTime != null ? Constants.runTime
								.toElapsedString() : "00:00:00"), 380, 320);
	}
}
