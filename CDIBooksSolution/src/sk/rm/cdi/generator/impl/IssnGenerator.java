package sk.rm.cdi.generator.impl;

import java.util.Random;
import java.util.logging.Logger;

import javax.inject.Inject;

import sk.rm.cdi.generator.NumberGenerator;
import sk.rm.cdi.qualifier.Generator;
import sk.rm.cdi.qualifier.Generator.Type;

@Generator(type=Type.ISSN)
public class IssnGenerator implements NumberGenerator {
	@Inject
	private Logger logger;

	@Override
	public String generateNumber() {
		String issn = "8-" + Math.abs(new Random().nextInt());
		logger.info("Generated ISSN : " + issn);
		return issn;
	}
}
