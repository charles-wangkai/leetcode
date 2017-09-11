import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ImplementMagicDictionary {
	private Set<String> words;

	/** Initialize your data structure here. */
	public ImplementMagicDictionary() {
	}

	/** Build a dictionary through a list of words */
	public void buildDict(String[] dict) {
		words = new HashSet<String>(Arrays.asList(dict));
	}

	/**
	 * Returns if there is any word in the trie that equals to the given word after
	 * modifying exactly one character
	 */
	public boolean search(String word) {
		for (int i = 0; i < word.length(); i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				if (ch == word.charAt(i)) {
					continue;
				}

				if (words.contains(word.substring(0, i) + ch + word.substring(i + 1))) {
					return true;
				}
			}
		}
		return false;
	}
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary(); obj.buildDict(dict); boolean
 * param_2 = obj.search(word);
 */