/* 
 * explains effectively final variable and consequence of the usage of this
 * keyword in lambda as opoosed to annonymous inner class
 */

package sk.rm.java8;

public class LambdaPitfalls {

	public void doIt() {

		// prior Java 8 the following var has to be final, since it is
		// referenced from inner class and/or lambda; as of now it is enough to
		// be effectively final - no changes of its value after initialization
		int var;
		var = 0;

		// uncomment this, var will become NOT effectively final
		//var++;

		// let's use var in anonymous inner class
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(var);
				System.out.println(this.getClass());
			}
		}).run();

		// previous written in lambda way + ! however check output to see
		// "this" in lambda references LambdaPitfalls object
		new Thread(() -> {
			System.out.println(var);
			System.out.println(this.getClass());
		}).run();
		
	}

	public static void main(String... args) {
		new LambdaPitfalls().doIt();
	}
}
