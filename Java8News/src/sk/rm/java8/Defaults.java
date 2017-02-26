/* 
 * shows usage of default methods, explains possible clashes in case there
 * are 2 or more identical default methods inherited
 * 
 * if you are wondering why there is no real practical example used - that
 * is the point...is there any? (unless you are neither creator of API desperately
 * trying to add functionality into your already existing interfaces
 * implemented by poor guys out there nor you are creator of java.util.function
 * package)
 */

package sk.rm.java8;

interface Doggable {
	default String makeNoise() {
		return "bark-bark";
	}
}

interface Cattable {
	default String makeNoise() {
		return "meeouw";
	}
}

// in case of clash implementing class is responsible for overriding behavior of
// the default method
class Monster implements Doggable, Cattable {
	@Override
	public String makeNoise() {
		// check out how super keyword is used if you want to choose which
		// default method will be used in case you are not providing your own
		// implementation - damn you Java8
		return Doggable.super.makeNoise();
	}
}

//
// Class wins rule
//
// in case class extends from another class with the same method name, default
// method from interface becomes irrelevant and there is no clash at all
class Monster2 extends AngryDog implements Cattable {

}

class AngryDog {
	public String makeNoise() {
		return "BARK-BARK";
	}
}

public class Defaults {
	public static void main(String... args) {
		System.out.println(new Monster().makeNoise());
		System.out.println(new Monster2().makeNoise());
	}
}
