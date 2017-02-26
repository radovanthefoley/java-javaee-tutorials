package sk.rm.cdi.producer;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class RandomNumberGenerator {
	
	private final Random random = new Random(System.currentTimeMillis());
	
	@Produces
	@RandomNumber
	public int next() {
		return random.nextInt();
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
