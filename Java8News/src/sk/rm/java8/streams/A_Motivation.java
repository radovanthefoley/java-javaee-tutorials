package sk.rm.java8.streams;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class A_Motivation {

	public static void main(String... args) {

		// suppose we are being provided by list of integers
		List<Integer> list = Arrays.asList(1, 2, 3);

		// let's do a sum over it in prior java 8 style
		Iterator<Integer> iter = list.iterator();
		int sum = 0;
		while (iter.hasNext()) {
			sum += iter.next();
		}
		System.out.println(sum); // 6

		// There are three major problems with the above approach:

		// 1. We just want to know the sum of integers but we would also have to
		// provide how the iteration will take place, this is also called
		// external iteration because client program is handling the algorithm
		// to iterate over the list.
		// 2. The program is sequential in nature, there is no way we can do
		// this in parallel easily.
		// 3. There is a lot of code to do even a simple task.

		// stream to the rescue!
		int sumByStream = list.stream().mapToInt(i -> i).sum();
		System.out.println(sumByStream); // 6

		// Internal iteration provides several features such as sequential and
		// parallel execution, filtering based on the given criteria, mapping
		// etc.
	}
}
