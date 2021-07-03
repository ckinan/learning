package collections;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			q.add(i);
		}

		System.out.println("queue size (before polling)=" + q.size());
		
		while (!q.isEmpty()) {
			System.out.println("value=" + q.poll());
		}
		
		System.out.println("queue size (after polling)=" + q.size());
	}

}
