package sk.rm.java8.streams;

import java.util.stream.Stream;

public class D_ProcessingOrder {

	public static void main(String... args) {

		// lazy!
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filterXXX: " + s);
			return true;
		});
		// nothing in console, since terminal method is missing

		// terminal method in place, notice vertical execution!
		// (no magic, it is only illusive, all happens within terminal function)
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		}).forEach(s -> System.out.println("forEach: " + s));
		// filter: d2
		// forEach: d2
		// filter: a2
		// forEach: a2
		// filter: b1
		// forEach: b1
		// filter: b3
		// forEach: b3
		// filter: c
		// forEach: c
		System.out.println();

		// vertical execution is desired for effective execution, if the
		// following was done horizontally, we would perform unnecessary mapping
		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).anyMatch(s -> {
			System.out.println("anyMatch: " + s);
			return s.startsWith("A");
		});
		// map: d2
		// anyMatch: D2
		// map: a2
		// anyMatch: A2
		System.out.println();

		// keep this in mind during designing of your own chains !!!
		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("A");
		}).forEach(s -> System.out.println("FINAL RESULT forEach: " + s));
		// map: d2
		// filter: D2
		// map: a2
		// filter: A2
		// FINAL RESULT forEach: A2
		// map: b1
		// filter: B1
		// map: b3
		// filter: B3
		// map: c
		// filter: C
		System.out.println();

		// the same with proper design
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("FINAL RESULT forEach: " + s));
		// filter: d2
		// filter: a2
		// map: a2
		// FINAL RESULT forEach: A2
		// filter: b1
		// filter: b3
		// filter: c
		System.out.println();

		// wait a sec, not so fast with verticalness! sorting operation requires
		// horizontal execution for very good reasons - to work :)
		Stream.of("d2", "a2", "b1", "b3", "c").sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("FINAL RESULT forEach: " + s));
		// sort: a2; d2
		// sort: b1; a2
		// sort: b1; d2
		// sort: b1; a2
		// sort: b3; b1
		// sort: b3; d2
		// sort: c; b3
		// sort: c; d2
		// filter: a2
		// map: a2
		// FINAL RESULT forEach: A2
		// filter: b1
		// filter: b3
		// filter: c
		// filter: d2
		System.out.println();

		// now with more efficient design, since sorting is called only for 1
		// element it will not even run
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		}).sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("FINAL RESULT forEach: " + s));
		// filter: d2
		// filter: a2
		// filter: b1
		// filter: b3
		// filter: c
		// map: a2
		// FINAL RESULT forEach: A2
		System.out.println();

		// RULE OF THUMB: stream operations are executed always vertically up to
		// point where sorting is needed, once sorting is done vertical execution continues
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("b");
		}).map(s -> {
			System.out.println("map1: " + s);
			return s + "_";
		}).sorted((s1, s2) -> {
			System.out.printf("sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		}).map(s -> {
			System.out.println("map2: " + s);
			return s.toUpperCase();
		}).forEach(s -> System.out.println("FINAL RESULT forEach: " + s));
		// filter: d2
		// filter: a2
		// filter: b1
		// map1: b1
		// filter: b3
		// map1: b3
		// filter: c
		// sort: b3_; b1_
		// map2: b1_
		// FINAL RESULT forEach: B1_
		// map2: b3_
		// FINAL RESULT forEach: B3_
	}
}
