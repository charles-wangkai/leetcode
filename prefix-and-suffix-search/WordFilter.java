import java.util.HashMap;
import java.util.Map;

public class WordFilter {
	Map<String, Integer> key2index = new HashMap<String, Integer>();

	public WordFilter(String[] words) {
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			for (int prefixLength = 0; prefixLength <= word.length(); prefixLength++) {
				for (int suffixLength = 0; suffixLength <= word.length(); suffixLength++) {
					key2index.put(
							generateKey(word.substring(0, prefixLength), word.substring(word.length() - suffixLength)),
							i);
				}
			}
		}
	}

	public int f(String prefix, String suffix) {
		String key = generateKey(prefix, suffix);
		return key2index.containsKey(key) ? key2index.get(key) : -1;
	}

	String generateKey(String prefix, String suffix) {
		return prefix + "|" + suffix;
	}
}

// Your WordFilter object will be instantiated and called as such:
// WordFilter obj = new WordFilter(words);
// int param_1 = obj.f(prefix,suffix);
