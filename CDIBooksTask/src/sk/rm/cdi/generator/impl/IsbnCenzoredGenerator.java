package sk.rm.cdi.generator.impl;

import java.util.logging.Logger;

// TODO
// ISBN type of generator
// should be deployment alternative
public class IsbnCenzoredGenerator extends IsbnGenerator {

	// TODO injection from producer (TODO create producer)
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public String generateNumber() {
		String isbn = "XX-XXXXX-XXXXXXXXXX";
		logger.info("Generated ISBN : " + isbn);
		return isbn;
	}
}
