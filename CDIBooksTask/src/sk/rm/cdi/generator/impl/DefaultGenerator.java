package sk.rm.cdi.generator.impl;

import java.util.logging.Logger;

import sk.rm.cdi.generator.NumberGenerator;

// TODO
// this should be the default instance
public class DefaultGenerator implements NumberGenerator {

	// TODO injection from producer (TODO create producer)
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public String generateNumber() {
		String number = "0";
		logger.info("Generated Mock : " + number);
		return number;
	}
}
