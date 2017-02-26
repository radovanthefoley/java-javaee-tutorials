package sk.rm.cdi.payment;

import sk.rm.cdi.interceptor.PerformanceMonitoring;


@PerformanceMonitoring
// @Interceptors(PerformanceInterceptor.class)
public class PaymentService {

	public void executeTimeConsumingTask() throws InterruptedException {
		Thread.sleep(1000);
	}

}
