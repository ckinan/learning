package others;

import java.util.HashSet;
import java.util.Set;

public class GenericTest {

	private static Set<Object> myObjects = new HashSet<Object>();
	
	public static void main(String[] args) {
		// Test 1 - Method using Generics
		print("hello");
		print(1);
		
		// Test 2 - Class using Generics
		MyGenericClass<String> stringGeneric = new GenericTest().new MyGenericClass<String>();
		stringGeneric.set("Value in my Generic Class");
		
		print(stringGeneric.get());
		
		MyGenericClass<Integer> integerGeneric = new GenericTest().new MyGenericClass<Integer>();
		integerGeneric.set(100);
		
		print(integerGeneric);
		
		// Test 3 - Casting - NonCasting - test
		String wordGeneric = putInMap_Generic("hello_generic");
		System.out.println(wordGeneric);
		Integer numberGeneric = putInMap_Generic(100);
		System.out.println(numberGeneric);
		
		String wordObject = (String) putInMap_Object("hello_object"); // Need of casting
		System.out.println(wordObject);
		Integer numberObject = (Integer) putInMap_Object(900); // Need of casting
		System.out.println(numberObject);
	}
	
	private static <T> void print(T input){
		System.out.println(input);
	}
	
	/*
	 * This method put the object in a Map, then return the object.
	 */
	private static <T> T putInMap_Generic(T input){
		myObjects.add(input);
		return input;
	}
	
	private static Object putInMap_Object(Object obj){
		myObjects.add(obj);
		return obj;
	}
	
	private class MyGenericClass<E>{
		private E e;
		public void set(E e){
			this.e = e;
		}
		public E get(){
			return this.e;
		}
		public String toString(){
			return e.toString();
		}
	}
	
}
