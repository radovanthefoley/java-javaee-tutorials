package sk.rm.cdi.generator.impl;

import java.util.Random;
import java.util.logging.Logger;

import sk.rm.cdi.generator.NumberGenerator;

// TODO
// ISSN type of generator
public class IssnGenerator implements NumberGenerator {

	// TODO injection from producer (TODO create producer)
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public String generateNumber() {
		String issn = "8-" + Math.abs(new Random().nextInt());
		logger.info("Generated ISSN : " + issn);
		return issn;
	}
}
