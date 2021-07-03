package collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	public static void main(String[] args) {
		/*
		 * http://docs.oracle.com/javase/tutorial/collections/interfaces/set.html
		 */
		
		// No guarantees concerning the order of iteration
		hashSet_test01();
		hashSet_test02();
		
		// Ordered using toCompare() method
		treeSet_test01();
		treeSet_test02();
		
		// Insertion order
		linkedHashSet_test01();
		linkedHashSet_test02();
	}

	private static void hashSet_test01() {
		System.out.println("*** hashSet_test01 ***");
		Set<String> s1 = new HashSet<String>();
		s1.add("value_01");
		s1.add("value_02");
		s1.add("value_01"); // Duplicate objects are prohibited
		Iterator<String> it1 = s1.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next());
		}
	}

	private static void hashSet_test02() {
		System.out.println("*** hashSet_test02 ***");
		Set<Customer> s1 = new HashSet<Customer>();
		Customer c1 = new Customer("Juan", 21);
		Customer c2 = new Customer("Maria", 36);
		s1.add(c1);
		s1.add(c2);
		s1.add(c1); // Duplicate objects are prohibited
		Iterator<Customer> it1 = s1.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next().toString());
		}
	}
	
	private static void treeSet_test01() {
		System.out.println("*** treeSet_test01 ***");
		Set<String> s1 = new TreeSet<String>();
		s1.add("value_01");
		s1.add("value_02");
		s1.add("value_01"); // Duplicate objects are prohibited
		Iterator<String> it1 = s1.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next());
		}
	}
	
	private static void treeSet_test02() {
		System.out.println("*** treeSet_test02 ***");
		Set<Customer> s1 = new TreeSet<Customer>();
		Customer c1 = new Customer("Juan", 45);
		Customer c2 = new Customer("Maria", 36);
		s1.add(c1);
		s1.add(c2);
		s1.add(c1); // Duplicate objects are prohibited
		Iterator<Customer> it1 = s1.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next().toString());
		}
	}
	
	private static void linkedHashSet_test01() {
		System.out.println("*** linkedHashSet_test01 ***");
		Set<String> s1 = new LinkedHashSet<String>();
		s1.add("value_01");
		s1.add("value_02");
		s1.add("value_01"); // Duplicate objects are prohibited. Re-insertion does not affect its order in the Set
		Iterator<String> it1 = s1.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next());
		}
	}
	
	private static void linkedHashSet_test02() {
		System.out.println("*** linkedHashSet_test02 ***");
		Set<Customer> s1 = new LinkedHashSet<Customer>();
		Customer c1 = new Customer("Juan", 45);
		Customer c2 = new Customer("Maria", 36);
		s1.add(c1);
		s1.add(c2);
		s1.add(c1); // Duplicate objects are prohibited. Re-insertion does not affect its order in the Set
		Iterator<Customer> it1 = s1.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next().toString());
		}
	}

}
