package sk.rm.cdi.interceptor;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@ResultLogging
public class ResultLoggingInterceptor {
	@Inject
	private Logger logger;

	@AroundInvoke
	public Object logMethod(InvocationContext ic) throws Exception {
		Object o = ic.proceed();
		if (o != null)
			logger.info(o.toString());
		return o;
	}
}