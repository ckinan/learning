package collections;

public class Customer implements Comparable<Customer> {

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + ", " + age;
	}

	// Compare by the name of the customer
//	@Override
//	public int compareTo(Customer o) {
//		return this.name.compareTo(o.getName());
//	}
	
	// Compare by the age of the customer
	@Override
	public int compareTo(Customer o) {
		return this.age.compareTo(o.getAge());
	}

}
