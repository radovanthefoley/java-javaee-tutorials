package sk.rm.cdi.generator.impl;

import java.util.Random;
import java.util.logging.Logger;

import sk.rm.cdi.generator.NumberGenerator;

// TODO
// ISBN type of generator
public class IsbnGenerator implements NumberGenerator {
	
	// TODO injection from producer (TODO create producer)
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public String generateNumber() {
		String isbn = "13-84356-" + Math.abs(new Random().nextInt());
		logger.info("Generated ISBN : " + isbn);
		return isbn;
	}
}
