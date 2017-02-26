package sk.rm.cdi.dynamicproducer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@RequestScoped
public class RandomNumberPrinter {
	
	@Inject
	@RandomNumber
	private Instance<Integer> randomNumber;
	
	void printRandomNumber() {
		System.out.println("random number: " + randomNumber.get());
	}
	
	@PostConstruct
	void initialize() {
		System.out.println(this + " initialized");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println(this + " destroyed");
	}
	
}
