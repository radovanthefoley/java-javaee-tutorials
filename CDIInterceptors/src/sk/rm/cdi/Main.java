package sk.rm.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import sk.rm.cdi.payment.PaymentService;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		PaymentService service = container.instance().select(PaymentService.class).get();
		service.executeTimeConsumingTask();
		weld.shutdown();
	}
}
