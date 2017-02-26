package sk.rm.cdi;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import sk.rm.cdi.model.Book;

public class BookRegisteringBean {

	@Inject
	Event<Book> event;

	public void registerBook(String title, Float price, String description) {
		event.fire(new Book(title, price, description));
	}
}
