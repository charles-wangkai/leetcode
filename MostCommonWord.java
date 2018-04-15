import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {
	public String mostCommonWord(String paragraph, String[] banned) {
		Set<String> bannedSet = new HashSet<String>(Arrays.asList(banned));

		String[] words = paragraph.toLowerCase().replaceAll("\\p{Punct}", "").split(" ");
		Map<String, Integer> word2count = new HashMap<String, Integer>();
		for (String word : words) {
			if (!bannedSet.contains(word)) {
				word2count.put(word, word2count.getOrDefault(word, 0) + 1);
			}
		}

		int maxCount = -1;
		String result = null;
		for (String word : word2count.keySet()) {
			int count = word2count.get(word);

			if (count > maxCount) {
				maxCount = count;
				result = word;
			}
		}
		return result;
	}
}
