package sk.rm.cdi.service;

import javax.inject.Inject;

import sk.rm.cdi.coder.Coder;

public class CodingService {

	@Inject
	Coder coder;

	public String encode(String s, int tval) {
		return coder.codeString(s, tval);
	}

}
