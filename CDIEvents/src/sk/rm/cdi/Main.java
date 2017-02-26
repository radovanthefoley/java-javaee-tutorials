package sk.rm.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import sk.rm.cdi.payment.PaymentBean;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		PaymentBean bean = container.instance().select(PaymentBean.class).get();
		bean.createCreditPayment(5.0);
		bean.createDebitPayment(10.0);
		weld.shutdown();
	}
}
