import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {
	private Map<String, List<Element>> keyToElements = new HashMap<>();

	/** Initialize your data structure here. */
	public TimeMap() {
	}

	public void set(String key, String value, int timestamp) {
		if (!keyToElements.containsKey(key)) {
			keyToElements.put(key, new ArrayList<>());
		}

		keyToElements.get(key).add(new Element(timestamp, value));
	}

	public String get(String key, int timestamp) {
		List<Element> elements = keyToElements.getOrDefault(key, new ArrayList<>());
		int index = Collections.binarySearch(elements, new Element(timestamp, null),
				(elem1, elem2) -> Integer.compare(elem1.timestamp, elem2.timestamp));
		if (index < 0) {
			index = -1 - index - 1;
		}

		return (index < 0) ? "" : elements.get(index).value;
	}
}

class Element {
	int timestamp;
	String value;

	Element(int timestamp, String value) {
		this.timestamp = timestamp;
		this.value = value;
	}
}

// Your TimeMap object will be instantiated and called as such:
// TimeMap obj = new TimeMap();
// obj.set(key,value,timestamp);
// String param_2 = obj.get(key,timestamp);
