package sk.rm.cdi.coder;

public class CoderImpl implements Coder {

	@Override
	public String codeString(String s, int tval) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			c += tval;
			sb.setCharAt(i, c);
		}
		return sb.toString();
	}

	@Override
	public Integer codeInt(Integer i, int tval) {
		throw new UnsupportedOperationException();
	}

}
