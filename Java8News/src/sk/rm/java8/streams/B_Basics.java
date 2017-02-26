package sk.rm.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import sk.rm.java8.streams.model.Person;

public class B_Basics {

	public static void main(String... args) {

		// streams can be created from various data sources, especially
		// collections
		// Lists and Sets support new method stream()
		List<String> myList = Arrays.asList("c1", "a2", "b1", "c2", "c2", "a1");

		// filtering + mapping + uniqueness + sorting
		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).distinct().sorted()
				.forEach(System.out::println);
		// C1
		// C2
		System.out.println();

		// iteration (see original list remains untouched, at least nothing is
		// filtered out)
		myList.stream().forEach(System.out::println);
		// c1
		// a2
		// b1
		// c2
		// c2
		// a1
		System.out.println();

		// do not be fooled though, it is possible to modify the list...
		List<Person> persons = Arrays.asList(new Person("Martin", 18), new Person("Peter", 23),
				new Person("Lucia", 26));
		persons.stream().peek(s -> s.name = "Bad Modification").forEach(System.out::println);
		// Bad Modification (18)
		// Bad Modification (23)
		// Bad Modification (26)
		System.out.println();
		persons.stream().forEach(System.out::println);
		// Bad Modification (18)
		// Bad Modification (23)
		// Bad Modification (26)
		System.out.println();
		// however this is considered as a bad design of the stream, you should
		// never modify underlying collection. Why is it dangerous? Just try to
		// remove terminal operation from modifying stream and nothing will
		// happen...
		// Rule of thumb: all the actions should be non-interfering!

		// Stream.of() can be used to create a stream from a bunch of object
		// references (of the same type)
		Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println);
		// a1
		System.out.println();

		// stream builder. This allows the creation of a Stream by generating
		// elements individually and adding them to the Builder (without the
		// copying overhead that comes from using an ArrayList as a temporary
		// buffer.)
		@SuppressWarnings("unused")
		Stream<Object> streamOfObj = Stream.builder().add("a").add(2).build();

		// streams for working with the primitive data types IntStream,
		// LongStream and DoubleStream
		IntStream.range(1, 4).forEach(System.out::println);
		// 1
		// 2
		// 3
		System.out.println();

		// legal question: why to use them? what is the difference for instance
		// between Stream<Integer> and IntStream?
		// answer: they are more efficient, unboxing is done only once at a
		// beginning, instead of every time when arithmetic operation is needed

		// primitive streams support the additional terminal aggregate
		// operations sum() and average()
		Arrays.stream(new int[] { 1, 2, 3 }).map(n -> 2 * n + 1).average().ifPresent(System.out::println);
		// 5.0
		System.out.println();

		// transforming a regular object stream to a primitive stream or vice
		// versa: mapToInt(), mapToLong() and mapToDouble()
		Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max()
				.ifPresent(System.out::println);
		// 3
		System.out.println();

		Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "a" + i).forEach(System.out::println);
		// a1
		// a2
		// a3
		System.out.println();

		// concat of two streams
		@SuppressWarnings("unused")
		Stream<Object> s = Stream.concat(Stream.of("foo"), Stream.of(new Object()));

		// generate a stream providing supplier function
		Stream<Double> randomStream = Stream.generate(Math::random);
		// be careful about infinite streams
		//randomStream.forEach(System.out::println);
		// shortcut operations for the rescue
		randomStream.limit(5).forEach(System.out::println);
		System.out.println();

		// create a stream by iterative application of a provided function over
		// initial seed
		Stream.iterate(0, i -> i + 3).limit(5).forEach(System.out::println);
		// 0
		// 3
		// 6
		// 9
		// 12
		System.out.println();

		// stream into array
		@SuppressWarnings("unused")
		Object[] arrOfObjects = Stream.of("a", "b", "c").toArray();

		// stream into supplied array
		@SuppressWarnings("unused")
		String[] arrOfStrings = Stream.of("a", "b", "c").toArray(String[]::new);

		// String into stream
		// Notice there is no CharStream in api, String.chars() return IntStream
		IntStream streamOfChars = "abc".chars();
		streamOfChars.forEach(c -> System.out.println(c + " -> " + Character.toString((char) c)));
		// 97 -> a
		// 98 -> b
		// 99 -> c
	}
}
