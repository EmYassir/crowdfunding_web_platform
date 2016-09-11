package fr.ensimag.ack.eggsale.web.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class HomeActionBean extends BaseActionBean {
	private static final String VIEW = VIEW_BASE_PATH + "home.jsp";

	public Resolution home() {
		return new ForwardResolution(VIEW);
	}
}
