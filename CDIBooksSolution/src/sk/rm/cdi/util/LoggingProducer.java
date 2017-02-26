package sk.rm.cdi.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

// Because Logger belongs to the JDK, it is not injectable by default 
// (the rt.jar file does not have a beans.xml file) and you then need to produce it.

public class LoggingProducer {
	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
