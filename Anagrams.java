import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
	public List<String> anagrams(String[] strs) {
		Map<String, List<String>> key2words = new HashMap<String, List<String>>();
		for (String str : strs) {
			String key = generateKey(str);
			if (!key2words.containsKey(key)) {
				key2words.put(key, new ArrayList<String>());
			}
			key2words.get(key).add(str);
		}

		List<String> result = new ArrayList<String>();
		for (List<String> words : key2words.values()) {
			if (words.size() > 1) {
				result.addAll(words);
			}
		}
		return result;
	}

	String generateKey(String word) {
		char[] letters = word.toCharArray();
		Arrays.sort(letters);
		return new String(letters);
	}
}
