package sk.rm.cdi.coder.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import sk.rm.cdi.coder.Coder;

@Decorator
public abstract class CoderDecorator implements Coder {

	@Inject
	@Delegate
	@Any
	Coder delegate;

	@Override
	public String codeString(String s, int tval) {
		int len = s.length();

		return "'" + s + "' becomes '" + delegate.codeString(s, tval) + "', " + len + " chars in length";
	}

}
