package others;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {

	public static void main(String[] args) {
		Person p = new Person("Juan", 22, "juan@example.com");
		serialize(p);
		deserialize(p);
	}

	private static void serialize(Person p) {
		try {
			FileOutputStream fileOut = new FileOutputStream("/tmp/java-practice-person.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(p);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in /tmp/java-practice-person.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private static void deserialize(Person p) {
		try {
			FileInputStream fileIn = new FileInputStream("/tmp/java-practice-person.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			p = (Person) in.readObject();
			in.close();
			fileIn.close();
			
			System.out.println("reading...");
			System.out.println(p.getName());
			System.out.println(p.getAge());
			System.out.println(p.getEmail());
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Person class not found");
			c.printStackTrace();
			return;
		}
	}

}
