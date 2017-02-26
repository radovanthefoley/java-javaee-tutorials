package sk.rm.java8.streams;

import java.util.function.Supplier;
import java.util.stream.Stream;

import sk.rm.java8.streams.model.Person;

public class F_ReduceFunction {

	private static Supplier<Stream<Person>> sup = () -> Stream.of(new Person("Martin", 18), new Person("Peter", 23),
			new Person("Lucia", 26), new Person("David", 12), new Person("Peter", 20));

	public static void main(String... args) {

		// the reduction operation combines all elements of the stream into a
		// single result

		// 1st version reduces a stream of elements to exactly one element of
		// the stream
		sup.get().reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
				.ifPresent((p) -> System.out.println("The oldest person is " + p));
		// The oldest person is Lucia (26)
		System.out.println();

		// Notice 1st version of reduce() returns Optional type (newly added in
		// Java 8). It is nothing more than a wrapper used in "what if null"
		// situations. Developers are encouraged to use it in their own code in
		// situations null is possible return value. Users of our api are
		// immediately aware of it just by looking into the code instead of
		// investigating documentation to learn whether null is legal return
		// value. Apparently NPE reduction is in place here.

		// 2nd version makes it possible to supply default return in case
		// reduction is completely eliminating action, or stream is eventually
		// empty
		Person youngestOverThirty = sup.get().filter(p -> p.age >= 30).reduce(new Person("James Bond", 34),
				(p1, p2) -> p1.age > p2.age ? p1 : p2);
		System.out.println("youngest person over thirty is " + youngestOverThirty);
		// youngest person over thirty is James Bond (34)
		System.out.println();

		// The same can be achieved by Stream.orElse()
		Person youngestOverThirty2 = sup.get().filter(p -> p.age >= 30).reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
				.orElse(new Person("James Bond", 34));
		System.out.println("youngest person over thirty is " + youngestOverThirty2);
		// youngest person over thirty is James Bond (34)
		System.out.println();

		// 3rd version uses BiFunction instead of BinaryOperator for
		// accumulation
		int ageSum = sup.get().reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
		System.out.println(ageSum);
		// 99
		System.out.println();

		// Again, combiner in 3rd version is not called at all in sequential
		// run:
		sup.get().reduce(0, (sum, p) -> {
			System.out.format("accumulator: \tsum=%s; \tperson=%s \t[%s]\n", sum, p, Thread.currentThread().getName());
			return sum += p.age;
		} , (sum1, sum2) -> {
			System.out.format("combiner: \tsum1=%s; \tsum2=%s \t[%s]\n", sum1, sum2, Thread.currentThread().getName());
			return sum1 + sum2;
		});
		System.out.println();

		// Simply there is no need to do it, all job is done by accumulator,
		// unless we are willing to use parallel streams:
		sup.get().parallel().reduce(0, (sum, p) -> {
			System.out.format("accumulator: \tsum=%s; \tperson=%s \t[%s]\n", sum, p, Thread.currentThread().getName());
			return sum += p.age;
		} , (sum1, sum2) -> {
			System.out.format("combiner: \tsum1=%s; \tsum2=%s \t[%s]\n", sum1, sum2, Thread.currentThread().getName());
			return sum1 + sum2;
		});
	}
}
