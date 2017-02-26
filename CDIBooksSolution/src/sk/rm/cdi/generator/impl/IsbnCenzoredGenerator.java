package sk.rm.cdi.generator.impl;

import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import sk.rm.cdi.qualifier.Generator;
import sk.rm.cdi.qualifier.Generator.Type;

@Alternative
@Specializes
@Generator(type = Type.ISBN)
public class IsbnCenzoredGenerator extends IsbnGenerator {
	@Inject
	private Logger logger;

	@Override
	public String generateNumber() {
		String isbn = "XX-XXXXX-XXXXXXXXXX";
		logger.info("Generated ISBN : " + isbn);
		return isbn;
	}
}
