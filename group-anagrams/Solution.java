import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> keyToWords = new HashMap<>();
		for (String str : strs) {
			String key = generateKey(str);
			if (!keyToWords.containsKey(key)) {
				keyToWords.put(key, new ArrayList<>());
			}

			keyToWords.get(key).add(str);
		}

		return new ArrayList<>(keyToWords.values());
	}

	String generateKey(String word) {
		return word.chars().sorted().mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.joining());
	}
}
