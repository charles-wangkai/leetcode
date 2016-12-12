import java.util.HashMap;
import java.util.Map;

public class EncodeStringWithShortestLength {
	Map<String, String> cache = new HashMap<String, String>();

	public String encode(String s) {
		if (cache.containsKey(s)) {
			return cache.get(s);
		}

		String result = s;

		int index = (s + s).indexOf(s, 1);
		if (index < s.length()) {
			String one = String.format("%d[%s]", s.length() / index, encode(s.substring(0, index)));

			if (one.length() < result.length()) {
				result = one;
			}
		}

		for (int i = 1; i <= s.length() - 1; i++) {
			String multi = encode(s.substring(0, i)) + encode(s.substring(i));

			if (multi.length() < result.length()) {
				result = multi;
			}
		}

		cache.put(s, result);
		return result;
	}
}