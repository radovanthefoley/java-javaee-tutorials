/* 
 * shows usage of method reference
 */

package sk.rm.java8;

import java.util.Arrays;
import java.util.Comparator;

public class MethodReferences {
	
	public static void main(String... args) {

		// pre Java 8
		Converter<String, Integer> converter = new Converter<String, Integer>() {

			@Override
			public Integer convert(String from) {
				return Integer.valueOf(from);
			}
		};

		// lambda for the rescue
		Converter<String, Integer> converterLambda = (a) -> Integer.valueOf(a);
		
		// but it just references already implemented method: vivat method reference!
		// TYPE: ContainingClass::staticMethodName 
		Converter<String, Integer> converterRef = Integer::valueOf;

		System.out.println(converter.convert("000"));
		System.out.println(converterLambda.convert("001"));
		System.out.println(converterRef.convert("002"));

		// -------------------------

		Adder adder = new Adder(5); // effectively final :)

		// pre Java 8
		Converter<Integer, Integer> converter2 = new Converter<Integer, Integer>() {

			@Override
			public Integer convert(Integer from) {
				return adder.execute(from);
			}
		};

		// lambda
		Converter<Integer, Integer> converter2Lambda = (a) -> adder.execute(a);

		// method reference
		// TYPE: containingObject::instanceMethodName
		Converter<Integer, Integer> converter2Ref = adder::execute;

		System.out.println(converter2.convert(1));
		System.out.println(converter2Lambda.convert(2));
		System.out.println(converter2Ref.convert(3));
		
		// the following is possible as well
		converter2Ref = new Adder(6)::execute;

		// -------------------------

		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };

		// pre Java 8
		Arrays.sort(stringArray, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});

		// lambda
		Arrays.sort(stringArray, (x, y) -> x.compareToIgnoreCase(y));

		// method reference
		// TYPE: ContainingType::methodName
		// NOTE: the first parameter becomes the target of the method
		Arrays.sort(stringArray, String::compareToIgnoreCase);
	}
}

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
}

class Adder {

	Adder(int addition) {
		this.addition = addition;
	}

	int addition;

	int execute(int x) {
		return x + addition;
	}
}
