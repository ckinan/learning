package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		arrayList_test01();
		linkedList_test01();
	}

	private static void arrayList_test01() {
		List<Customer> myList = new ArrayList<Customer>();
		for (int i = 0; i < 1000000; i++) {
			myList.add(new Customer("Customer's name", 20));
		}
		double start = System.nanoTime();
		myList.add(0, new Customer("Customer's beta name", 30));
		double end = System.nanoTime();
		System.out.println("Time in arrayList_test01() : " + (end - start));
	}
	
	private static void linkedList_test01() {
		List<Customer> myList = new LinkedList<Customer>();
		for (int i = 0; i < 1000000; i++) {
			myList.add(new Customer("Customer's name", 20));
		}
		double start = System.nanoTime();
		myList.add(0, new Customer("Customer's beta name", 30));
		double end = System.nanoTime();
		System.out.println("Time in linkedList_test01() : " + (end - start));
	}

}
