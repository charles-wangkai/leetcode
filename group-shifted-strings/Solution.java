import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public List<List<String>> groupStrings(String[] strings) {
		Map<String, List<String>> key2group = new HashMap<String, List<String>>();
		for (String str : strings) {
			String key = generateKey(str);
			if (!key2group.containsKey(key)) {
				key2group.put(key, new ArrayList<String>());
			}
			key2group.get(key).add(str);
		}

		List<List<String>> result = new ArrayList<List<String>>();
		for (List<String> group : key2group.values()) {
			Collections.sort(group);
			result.add(group);
		}
		return result;
	}

	String generateKey(String str) {
		int offset = ('a' - str.charAt(0) + 26) % 26;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			sb.append((char) ((str.charAt(i) + offset) % 26));
		}
		return sb.toString();
	}
}
