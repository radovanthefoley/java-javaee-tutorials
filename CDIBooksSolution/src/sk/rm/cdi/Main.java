package sk.rm.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
	public static void main(String[] args) {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		BookRegisteringBean bookBean = container.instance().select(BookRegisteringBean.class).get();
		bookBean.registerBook("H2G2", 12.5f, "Geeky scifi Book");
		weld.shutdown();
	}
}
