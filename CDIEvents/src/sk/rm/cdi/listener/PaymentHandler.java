package sk.rm.cdi.listener;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;

import sk.rm.cdi.event.PaymentEvent;
import sk.rm.cdi.payment.qualifier.Credit;
import sk.rm.cdi.payment.qualifier.Debit;

public class PaymentHandler {

	public void processCreditPayment(@Observes @Credit PaymentEvent event) {
		System.out.println("processing credit event: " + event);
		System.out.println(this);
	}

	public void processDebitPayment(@Observes @Debit PaymentEvent event) {
		System.out.println("processing debit event: " + event);
		System.out.println(this);
	}

	public void auditPayments(@Observes @Any PaymentEvent event) {
		System.out.println("auditing payment event: " + event);
		System.out.println(this);
	}
}
