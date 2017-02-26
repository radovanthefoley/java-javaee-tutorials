package sk.rm.java8.streams;

import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sk.rm.java8.streams.model.Person;

public class E_CollectFunction {

	private static Supplier<Stream<Person>> sup = () -> Stream.of(new Person("Martin", 18), new Person("Peter", 23),
			new Person("Lucia", 23), new Person("David", 12), new Person("Peter", 20));

	public static void main(String... args) {

		// collect is useful terminal reducing operation that stores products of
		// the stream chain into desired objects.
		// It requires a Collector which consists of four different operations:
		// a supplier, an accumulator, a combiner and a finisher. The good news
		// is java ships with number of predefined collectors sets out of the
		// box

		// Set accumulation
		Set<Person> filteredByName = sup.get().filter(p -> p.name.startsWith("P")).collect(Collectors.toSet());
		System.out.println(filteredByName);
		// [Peter (23), Peter (20)]
		System.out.println(filteredByName.getClass().getName());
		// java.util.HashSet
		System.out.println();

		// List accumulation
		List<Person> filteredByAgeSorted = sup.get().filter(p -> p.age >= 18).sorted(Person::compareTo)
				.collect(Collectors.toList());
		System.out.println(filteredByAgeSorted);
		// [Lucia (23), Martin (18), Peter (20), Peter (23)]
		System.out.println(filteredByAgeSorted.getClass().getName());
		// java.util.ArrayList
		System.out.println();

		// If specific collection implementation is desired
		LinkedList<Person> listOfPersons = sup.get().collect(Collectors.toCollection(LinkedList::new));
		System.out.println(listOfPersons);
		// [Martin (18), Peter (23), Lucia (23), David (12), Peter (20)]
		System.out.println(listOfPersons.getClass().getName());
		// java.util.LinkedList
		System.out.println();

		// Map creation
		// once using the basic form, the keys have to be unique, otherwise...
		Map<Integer, String> personsByAge = null;
		try {
			personsByAge = sup.get().collect(Collectors.toMap(p -> p.age, p -> p.name));
		} catch (IllegalStateException e) {
			System.out.println(e);
			// java.lang.IllegalStateException: Duplicate key Peter
			// key? Peter? bug :) ?
			System.out.println();
		}
		// to overcome this, let's use a variant with merge function attribute
		personsByAge = sup.get()
				.collect(Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> name1 + "," + name2));
		personsByAge.forEach((age, name) -> System.out.format("age %s: %s\n", age, name));
		// age 18: Martin
		// age 20: Peter
		// age 23: Peter,Lucia
		// age 12: David
		System.out.println(personsByAge.getClass().getName());
		// java.util.HashMap
		System.out.println();
		// interested in concrete map implementation? just supply it
		personsByAge = sup.get().collect(
				Collectors.toMap(p -> p.age, p -> p.name, (name1, name2) -> (name1 + "," + name2), TreeMap::new));
		personsByAge.forEach((age, name) -> System.out.format("age %s: %s\n", age, name));
		// age 12: David
		// age 18: Martin
		// age 20: Peter
		// age 23: Peter,Lucia
		System.out.println(personsByAge.getClass().getName());
		// java.util.TreeMap
		System.out.println();

		// Map creation by grouping
		Map<Integer, List<Person>> groupedByAge = sup.get().collect(Collectors.groupingBy(p -> p.age));
		groupedByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		// age 18: [Martin (18)]
		// age 20: [Peter (20)]
		// age 23: [Peter (23), Lucia (23)]
		// age 12: [David (12)]
		System.out.println();

		// Grouping with reduce operation
		Map<Integer, Long> groupedByAgeCounting = sup.get()
				.collect(Collectors.groupingBy(p -> p.age, Collectors.counting()));
		groupedByAgeCounting.forEach((age, n) -> System.out.format("age %s: %s\n", age, n));
		// age 18: 1
		// age 20: 1
		// age 23: 2
		// age 12: 1
		System.out.println();

		// Basic statistic
		double averageAge = sup.get().collect(Collectors.averagingInt(p -> p.age));
		System.out.println("average age: " + averageAge);
		// average age: 19.2
		System.out.println();

		// More comprehensive statistic
		IntSummaryStatistics statistic = sup.get().collect(Collectors.summarizingInt(p -> p.age));
		System.out.println(statistic);
		// IntSummaryStatistics{count=5, sum=96, min=12, average=19.200000,
		// max=23}
		System.out.println();

		// Reduction to single String
		String message = sup.get().filter(p -> p.age >= 18).map(Person::toString)
				.collect(Collectors.joining(", ", "In Slovakia only ", " can buy alcohol legally."));
		System.out.println(message);
		// In Slovakia only Martin (18), Peter (23), Lucia (23), Peter (20) can
		// buy alcohol legally.
		System.out.println();

		// Customized Collector
		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "), // supplier
																													// -
																													// structure
																													// to
																													// accumulate
																													// to
				(j, p) -> j.add(p.name.toUpperCase()), // accumulator - used to
														// fill up supplier
				(j1, j2) -> j1.merge(j2), // combiner - needed in case of
											// parallel stream runs to merge the
											// results
				StringJoiner::toString); // finisher - the actual result object
		String names = sup.get().collect(personNameCollector);
		System.out.println(names);
		// MARTIN | PETER | LUCIA | DAVID | PETER
	}
}
