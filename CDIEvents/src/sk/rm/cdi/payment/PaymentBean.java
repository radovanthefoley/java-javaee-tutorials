package sk.rm.cdi.payment;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import sk.rm.cdi.event.PaymentEvent;
import sk.rm.cdi.payment.qualifier.Credit;
import sk.rm.cdi.payment.qualifier.Debit;

public class PaymentBean {

	@Inject
	@Credit
	Event<PaymentEvent> creditEvent;

	@Inject
	@Debit
	Event<PaymentEvent> debitEvent;

	public void createCreditPayment(double sum) {
		creditEvent.fire(new PaymentEvent(sum, "credit"));
	}

	public void createDebitPayment(double sum) {
		debitEvent.fire(new PaymentEvent(sum, "debit"));
	}
}
