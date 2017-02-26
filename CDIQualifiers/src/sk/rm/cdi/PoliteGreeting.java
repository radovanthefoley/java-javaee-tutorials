package sk.rm.cdi;

import javax.enterprise.inject.Default;

@Default
public class PoliteGreeting implements Greeting {

	@Override
	public String greet(String name) {
		return "Hello, " + name + ".";
	}

}
