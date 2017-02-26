package sk.rm.cdi.coder;

public interface Coder {

	String codeString(String s, int tval);

	Integer codeInt(Integer i, int tval);
}
