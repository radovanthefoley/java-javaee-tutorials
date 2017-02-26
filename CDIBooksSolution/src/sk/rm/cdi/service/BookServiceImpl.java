package sk.rm.cdi.service;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import sk.rm.cdi.generator.NumberGenerator;
import sk.rm.cdi.interceptor.ResultLogging;
import sk.rm.cdi.model.Book;
import sk.rm.cdi.qualifier.Generator;
import sk.rm.cdi.qualifier.Generator.Type;

public class BookServiceImpl implements BookService {

	@Inject
	@Generator(type = Type.ISBN)
	private NumberGenerator numberGenerator;

	@ResultLogging
	@Override
	public Book registerBook(@Observes Book book) {
		book.setNumber(numberGenerator.generateNumber());
		return book;
	}
}
