import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public int maxProduct(String[] words) {
		Map<Integer, Integer> key2length = Arrays.stream(words)
				.collect(Collectors.toMap(this::generateKey, String::length, Math::max));

		int result = 0;
		for (int key1 : key2length.keySet()) {
			for (int key2 : key2length.keySet()) {
				if ((key1 & key2) == 0) {
					result = Math.max(result, key2length.get(key1) * key2length.get(key2));
				}
			}
		}
		return result;
	}

	int generateKey(String word) {
		return word.chars().map(ch -> 1 << (ch - 'a')).reduce(0, (x, y) -> x | y);
	}
}
