import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> pairs = new ArrayList<List<Integer>>();

		Map<String, Integer> word2index = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			word2index.put(words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			searchForRight(pairs, words, word2index, i);
			searchForLeft(pairs, words, word2index, i);
		}

		return pairs;
	}

	void searchForRight(List<List<Integer>> pairs, String[] words, Map<String, Integer> word2index, int index) {
		for (int middleLength = 0; middleLength <= words[index].length(); middleLength++) {
			String left = words[index].substring(0, words[index].length() - middleLength);
			String middle = words[index].substring(words[index].length() - middleLength);

			if (!isPalindrome(middle)) {
				continue;
			}

			String right = reverse(left);
			if (!word2index.containsKey(right) || word2index.get(right) == index) {
				continue;
			}

			List<Integer> pair = new ArrayList<Integer>();
			pair.add(index);
			pair.add(word2index.get(right));
			pairs.add(pair);
		}
	}

	void searchForLeft(List<List<Integer>> pairs, String[] words, Map<String, Integer> word2index, int index) {
		for (int middleLength = 1; middleLength <= words[index].length(); middleLength++) {
			String right = words[index].substring(middleLength);
			String middle = words[index].substring(0, middleLength);

			if (!isPalindrome(middle)) {
				continue;
			}

			String left = reverse(right);
			if (!word2index.containsKey(left) || word2index.get(left) == index) {
				continue;
			}

			List<Integer> pair = new ArrayList<Integer>();
			pair.add(word2index.get(left));
			pair.add(index);
			pairs.add(pair);
		}
	}

	String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	boolean isPalindrome(String str) {
		return str.equals(reverse(str));
	}
}
