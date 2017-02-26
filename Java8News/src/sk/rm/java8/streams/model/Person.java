package sk.rm.java8.streams.model;

public class Person implements Comparable<Person> {
	public String name;
	public int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + " (" + age + ")";
	}

	@Override
	public int compareTo(Person that) {
		if (this == that)
			return 0;
		// name first
		int result = this.name.compareTo(that.name);
		// age if name equality
		if (result == 0)
			result = Integer.valueOf(this.age).compareTo(Integer.valueOf(that.age));
		// should be equal now
		if (result == 0)
			assert this.equals(that) : "compareTo not consitent with equals!";
			
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}