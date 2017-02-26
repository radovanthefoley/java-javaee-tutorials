package sk.rm.cdi.service;

import sk.rm.cdi.generator.NumberGenerator;
import sk.rm.cdi.generator.impl.DefaultGenerator;
import sk.rm.cdi.model.Book;

public class BookServiceImpl implements BookService {

	// TODO inject
	private NumberGenerator numberGenerator = new DefaultGenerator();

	// TODO
	// intercept this method to log resulting book details
	// TODO
	// make this method to listen when a new book is created
	@Override
	public Book registerBook(Book book) {
		book.setNumber(numberGenerator.generateNumber());
		return book;
	}
}
