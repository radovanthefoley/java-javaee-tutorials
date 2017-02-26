package sk.rm.cdi.event;

import java.io.Serializable;

public class PaymentEvent implements Serializable {

	private static final long serialVersionUID = 769766963886783871L;

	private double sum;
	private String type;

	public PaymentEvent(double sum, String type) {
		this.sum = sum;
		this.type = type;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PaymentEvent [sum=" + sum + ", type=" + type + "]";
	}

}
