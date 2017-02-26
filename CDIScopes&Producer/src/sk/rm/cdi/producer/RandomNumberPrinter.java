package sk.rm.cdi.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class RandomNumberPrinter {
	
	@Inject
	@RandomNumber
	private int randomNumber;
	
	void printRandomNumber() {
		System.out.println("random number: " + randomNumber);
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
