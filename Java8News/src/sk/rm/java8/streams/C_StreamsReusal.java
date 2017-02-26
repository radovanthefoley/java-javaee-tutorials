package sk.rm.java8.streams;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class C_StreamsReusal {

	@SuppressWarnings("unused")
	public static void main(String... args) {

		// stream object cannot be reused once terminal operation is called
		Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c");
		long countA = stream.filter(s -> s.startsWith("a")).count(); // ok
		try {
			long countB = stream.filter(s -> s.startsWith("b")).count(); // exception
		} catch (IllegalStateException e) {
			System.out.println(e);
		}

		// instead java.util.function.Supplier should be used
		Supplier<Stream<String>> sup = () -> Stream.of("d2", "a2", "b1", "b3", "c");
		// to obtain desired, Supplier provides method get()
		System.out.println(sup.get().filter(s -> s.startsWith("a")).count());
		//1
		System.out.println(sup.get().filter(s -> s.startsWith("b")).count());
		//2
	}
}
