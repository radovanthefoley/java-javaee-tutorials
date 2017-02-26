package sk.rm.cdi;

import javax.inject.Inject;

public class GreetingService {
	
	@Inject
	//@Informal
	Greeting greeting;
	
	void printGreet(String name) {
		System.out.println(greeting.greet(name));
	}
}
