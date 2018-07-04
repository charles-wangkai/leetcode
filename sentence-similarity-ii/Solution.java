import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
		if (words1.length != words2.length) {
			return false;
		}

		Map<String, String> word2parent = new HashMap<String, String>();
		for (String[] pair : pairs) {
			String root1 = findRoot(word2parent, pair[0]);
			String root2 = findRoot(word2parent, pair[1]);

			if (!root1.equals(root2)) {
				word2parent.put(root1, root2);
			}
		}
		return words1.length == words2.length && IntStream.range(0, words1.length)
				.allMatch(i -> findRoot(word2parent, words1[i]).equals(findRoot(word2parent, words2[i])));
	}

	String findRoot(Map<String, String> word2parent, String word) {
		String root = word;
		while (word2parent.containsKey(root)) {
			root = word2parent.get(root);
		}

		String s = word;
		while (!s.equals(root)) {
			String nextS = word2parent.get(s);
			word2parent.put(s, root);
			s = nextS;
		}

		return root;
	}
}
