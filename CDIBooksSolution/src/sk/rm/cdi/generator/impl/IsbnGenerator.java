package sk.rm.cdi.generator.impl;

import java.util.Random;
import java.util.logging.Logger;

import javax.inject.Inject;

import sk.rm.cdi.generator.NumberGenerator;
import sk.rm.cdi.qualifier.Generator;
import sk.rm.cdi.qualifier.Generator.Type;

@Generator(type=Type.ISBN)
public class IsbnGenerator implements NumberGenerator {
	@Inject
	private Logger logger;

	@Override
	public String generateNumber() {
		String isbn = "13-84356-" + Math.abs(new Random().nextInt());
		logger.info("Generated ISBN : " + isbn);
		return isbn;
	}
}
