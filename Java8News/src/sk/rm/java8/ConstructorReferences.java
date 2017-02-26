/* 
 * shows usage of constructor reference
 */

package sk.rm.java8;

public class ConstructorReferences {

	@SuppressWarnings("unused")
	public static void main(String... args) {
		
		// pre Java 8
		AnimalFactory<Dog> factory = new AnimalFactory<Dog>() {
			@Override
			public Dog create() {
				return new Dog();
			}
		};
		
		// lambda, again the only task is to call already existing method (constructor this time)
		AnimalFactory<Dog> factoryLambda = () -> new Dog();
		
		// constructor reference
		AnimalFactory<Dog> factoryRef = Dog::new;
	}
}

@FunctionalInterface
interface AnimalFactory<T extends Animal> {
	T create();
}

class Animal {
}

class Dog extends Animal {
}
