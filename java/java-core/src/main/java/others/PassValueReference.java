package others;

public class PassValueReference {

	public static void main(String[] args) {
		//create an object by passing in a name and age:
		PersonClass variable1 = new PersonClass("Mary", 32);
		PersonClass variable2;
		variable2 = variable1; 
		PersonClass variable3 = new PersonClass("Andre", 45);
		variable1 = variable3;
		System.out.println(variable2);
		System.out.println(variable1);
	}

}

class PersonClass{
	private String name;
	private int age;
	public PersonClass(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return (name + " " + age);
	}
}