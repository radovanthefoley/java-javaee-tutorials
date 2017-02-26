/* 
 * shows usage of java.lang.annotation.Repeatable @Interface and approaching style via reflection
 */

package sk.rm.java8;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RepeatableAnnotations {
	public static void main(String... args) {
		
		// prior Java 8: obtaining value from nested annotations
		Hints hints1 = EJB2.class.getAnnotation(Hints.class);
		System.out.println(hints1.value().length); // 2
		System.out.println(hints1.value()[0].value()); // forget		
		// impossible to do it directly
		Hint hint = EJB2.class.getAnnotation(Hint.class);
		System.out.println(hint); // null

		// getAnnotationsByType method added in Java 8 in order to obtain
		// Repeatable Annotations more easily
		Hint[] hints2 = EJB3.class.getAnnotationsByType(Hint.class);
		System.out.println(hints2.length); // 2
		System.out.println(hints2[0].value()); // learn
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface Hints {
	Hint[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Hints.class)
@interface Hint {
	String value();
}

// Prior Java 8
@Hints({ @Hint("forget"), @Hint("forget even faster") })
class EJB2 {
}

// Java 8 usage thanks to @Repeatable
@Hint("learn")
@Hint("learn even faster")
class EJB3 {
}