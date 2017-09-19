import java.util.HashMap;
import java.util.Map;

public class MapSumPairs {
	Map<String, Integer> map = new HashMap<String, Integer>();

	/** Initialize your data structure here. */
	public MapSumPairs() {
	}

	public void insert(String key, int val) {
		map.put(key, val);
	}

	public int sum(String prefix) {
		return map.entrySet().stream().filter(entry -> entry.getKey().startsWith(prefix)).mapToInt(Map.Entry::getValue)
				.sum();
	}
}

/**
 * Your MapSum object will be instantiated and called as such: MapSum obj = new
 * MapSum(); obj.insert(key,val); int param_2 = obj.sum(prefix);
 */