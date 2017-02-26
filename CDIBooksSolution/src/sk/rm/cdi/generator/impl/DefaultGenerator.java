package sk.rm.cdi.generator.impl;

import java.util.logging.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import sk.rm.cdi.generator.NumberGenerator;

@Default
public class DefaultGenerator implements NumberGenerator {
	@Inject
	private Logger logger;

	@Override
	public String generateNumber() {
		String number = "0";
		logger.info("Generated Mock : " + number);
		return number;
	}
}
