package sk.rm.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import sk.rm.cdi.service.CodingService;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		CodingService service = container.instance().select(CodingService.class).get();
		System.out.println(service.encode("Capco", 5));
		weld.shutdown();
	}
}
