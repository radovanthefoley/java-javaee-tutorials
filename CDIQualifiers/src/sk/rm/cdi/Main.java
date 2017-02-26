package sk.rm.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		GreetingService service = container.instance().select(GreetingService.class).get();
		service.printGreet("John Shtoor");
		weld.shutdown();
	}
}
