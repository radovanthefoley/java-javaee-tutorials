package sk.rm.cdi;

import javax.enterprise.inject.Alternative;

//@Informal
@Alternative
public class InformalGreeting implements Greeting {

	@Override
	public String greet(String name) {
		return "Hi, " + name + "!";
	}

}
