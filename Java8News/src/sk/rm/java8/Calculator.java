/* 
 * shows usage of @FunctionalInterface annotation
 * different styles of lambda definitions
 */

package sk.rm.java8;

public class Calculator {

	// @FunctionalInterface annotation not required, uncomment 2nd method, check
	// compilation errors and then comment out the annotation to see the
	// difference

	@FunctionalInterface
	interface IntegerMath {
		int operation(int a, int b);
		//int definitelyDoNotUncomentMe();
	}

	public int operateBinary(int a, int b, IntegerMath op) {
		return op.operation(a, b);
	}

	public static void main(String... args) {

		Calculator myApp = new Calculator();
		
		// pre Java 8
		IntegerMath modulo = new IntegerMath() {
			
			@Override
			public int operation(int a, int b) {
				return a % b;
			}
		};
		
		System.out.println("41 % 2 = " + myApp.operateBinary(41, 2, modulo));

		// let's define some lambdas
		// as much explicit as possible (who wants to write/read this?)

		IntegerMath addition = (int a, int b) -> {
			return a + b;
		};

		// let's save some typing... (types derived from functional
		// interface), even return keyword omitted

		IntegerMath subtraction = (a, b) -> a - b;

		System.out.println("40 + 2 = " + myApp.operateBinary(40, 2, addition));
		System.out.println("20 - 10 = "
				+ myApp.operateBinary(20, 10, subtraction));

		// usually you will use it without previous declaration (analogy with
		// anonymous inner class)

		System.out.println("40 * 2 = "
				+ myApp.operateBinary(40, 2, (a, b) -> a * b));

		// {} needed if lambda consists of multiple commands

		System.out.println("40 / 2 = " + myApp.operateBinary(40, 2, (a, b) -> {
			System.out.println("Oh man, I came from lambda");
			return a / b;
		}));
	}
}
