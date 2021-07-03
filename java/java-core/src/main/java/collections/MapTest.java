package collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("key1", 10);
		m.put("key2", 20);

		Set<String> s = m.keySet();
		Iterator<String> it = s.iterator();
		while (it.hasNext()) {
			String currentKey = it.next();
			System.out.println(currentKey + "=" + m.get(currentKey));
		}
		
		System.out.println("----");
		for (Map.Entry<String, Object> me : m.entrySet()) {
			System.out.println(me.getKey() + "=" + me.getValue());
		}
	}

}
