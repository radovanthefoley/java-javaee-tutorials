package sk.rm.cdi.producer;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.UnboundLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();

		RandomNumberPrinter service;

		// simulation of request 1
		RequestContext rc1 = container.instance().select(RequestContext.class, UnboundLiteral.INSTANCE).get();
		rc1.activate();
		System.out.println(">>> request 1 started");
		service = container.instance().select(RandomNumberPrinter.class).get();
		service.printRandomNumber();
		service.printRandomNumber();
		rc1.invalidate();
		rc1.deactivate();
		System.out.println(">>> request 1 finished");

		// simulation of request 2
		RequestContext rc2 = container.instance().select(RequestContext.class, UnboundLiteral.INSTANCE).get();
		rc2.activate();
		System.out.println(">>> request 2 started");
		service.printRandomNumber();
		service.printRandomNumber();
		rc2.invalidate();
		rc2.deactivate();
		System.out.println(">>> request 2 finished");

		weld.shutdown();
	}
}
