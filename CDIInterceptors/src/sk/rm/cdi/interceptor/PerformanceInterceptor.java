package sk.rm.cdi.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@PerformanceMonitoring
public class PerformanceInterceptor {

	@AroundInvoke
	public Object around(InvocationContext ic) throws Exception {
		System.out.println("entering: " + ic.getTarget().getClass().getName() + "." + ic.getMethod().getName());
		long start = System.currentTimeMillis();
		try {
			return ic.proceed();
		} finally {
			long stop = System.currentTimeMillis();
			System.out.println("exiting: " + ic.getTarget().getClass().getName() + "." + ic.getMethod().getName());
			long runtime = stop - start;
			System.out.println("performance: " + ic.getTarget().getClass().getName() + "." + ic.getMethod().getName()
					+ " took " + runtime + "ms");
		}
	}

	@PostConstruct
	public void creation(InvocationContext ic) {
		System.out.println("constructed: " + ic.getTarget().getClass().getName());
	}

	@PreDestroy
	public void destroying(InvocationContext ic) {
		System.out.println("about to be destroyed: " + ic.getTarget().getClass().getName());
	}
}
