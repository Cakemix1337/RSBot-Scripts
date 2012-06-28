package kTinderBox.functions.strategies;

import kTinderBox.functions.Constants;
import kTinderBox.functions.handler.Methods;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Buy extends Strategy implements Task {

	public boolean validate() {
		if (Constants.STOP_SCRIPT)
			return false;

		Methods.openTab(Tabs.INVENTORY);

		if (Widgets.get(Constants.WIDGET_SHOP)
				.getChild(Constants.WIDGET_TINDERBOX).validate())
			return true;

		return false;
	}

	public void run() {
		Widget widget = Widgets.get(Constants.WIDGET_SHOP);

		if (widget == null) // What the fuck?
			return;

		if (!Constants.INVENTORY_FULL) {
			if (widget.getChild(28).getHeight() != 22) {
				// We're in the sell tab...
				widget.getChild(86).click(true);
				return;
			}
			WidgetChild widgetChild = widget.getChild(
					Constants.WIDGET_TINDERBOX).getChild(0);

			if (widgetChild.getChildStackSize() != -1) {
				if (widgetChild.getChildStackSize() < 20) {
					Constants.STOP_SCRIPT = true;
					return;
				}

				Constants.TINDERBOX_LEFT = widgetChild.getChildStackSize();

				if (widgetChild.interact("Buy All")
						&& widget.getChild(86).click(true)) {
					Constants.INVENTORY_FULL = false;
					Time.sleep(256, 512);
				}

			}
		} else {
			WidgetChild widgetChild = widget.getChild(86);
			if (widgetChild.validate()) {
				widgetChild.click(true);
			}

			Constants.INVENTORY_FULL = false;
		}
	}
}
