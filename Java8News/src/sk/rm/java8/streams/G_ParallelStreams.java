package sk.rm.java8.streams;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import sk.rm.java8.streams.model.Person;

public class G_ParallelStreams {

	private static Supplier<Stream<Person>> sup = () -> Stream.of(new Person("Jan", 18), new Person("Peter", 23),
			new Person("Lucia", 26), new Person("David", 12), new Person("Peter", 20));

	public static void main(String... args) {

		// number of additional parallel workers
		System.out.println("number of additional parallel workers: " + ForkJoinPool.commonPool().getParallelism());
		System.out.println();

		// -Djava.util.concurrent.ForkJoinPool.common.parallelism JVM attribute
		// can be used to change it (usual default is 3)
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		System.out.println("input arguments: " + arguments);
		System.out.println();

		// how to obtain parallel stream:

		// collections support parallelStream method
		Arrays.asList("foo").parallelStream();

		// sequential stream can be "upgraded"
		Stream.of("bar").parallel();

		// or vice versa
		@SuppressWarnings("unused")
		UnaryOperator<Stream<?>> evilSwitcher = s -> {
			if (s.isParallel())
				return s.sequential();
			else
				return s.parallel();
		};

		// study case to see execution mechanism
		sup.get().parallel().filter(s -> {
			System.out.format(" filter: \t%s \t[%s]\n", s, Thread.currentThread().getName());
			return true;
		}).map(s -> {
			System.out.format("    map: \t%s \t[%s]\n", s, Thread.currentThread().getName());
			return s.name.toUpperCase();
		}).forEach(s -> System.out.format("forEach: \t%s \t\t[%s]\n", s, Thread.currentThread().getName()));
		System.out.println();

		// adding sorting
		sup.get().parallel().filter(s -> {
			System.out.format(" filter: \t%s \t[%s]\n", s, Thread.currentThread().getName());
			return true;
		}).map(s -> {
			System.out.format("    map: \t%s \t[%s]\n", s, Thread.currentThread().getName());
			return s.name.toUpperCase();
		}).sorted((s1, s2) -> {
			System.out.format("   sort: \t%s <> %s \t[%s]\n", s1, s2, Thread.currentThread().getName());
			return s1.compareTo(s2);
		}).forEach(s -> System.out.format("forEach: \t%s \t\t[%s]\n", s, Thread.currentThread().getName()));
		System.out.println();

		// most probably sorting is done within main thread only (as expected).
		// However, sort on a parallel stream uses the new Java 8 method
		// Arrays.parallelSort() under the hood. As stated in Javadoc this
		// method decides on the length of the array if sorting will be
		// performed sequentially or in parallel: If the length of the specified
		// array is less than the minimum granularity, then it is sorted using
		// the appropriate Arrays.sort method.
		// From the blogs: required minimum is 2 millions!

		// In summary, it can be stated that parallel streams can bring a
		// nice performance boost to streams with a large amount of input
		// elements. But keep in mind that some parallel stream operations like
		// reduce and collect need additional computations (combine operations)
		// which isn't needed when executed sequentially.
	}

}
