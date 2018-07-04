import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public List<List<String>> wordSquares(String[] words) {
		int size = words[0].length();

		Map<String, List<String>> prefix2words = new HashMap<String, List<String>>();
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				String prefix = word.substring(0, i);
				if (!prefix2words.containsKey(prefix)) {
					prefix2words.put(prefix, new ArrayList<String>());
				}
				prefix2words.get(prefix).add(word);
			}
		}

		List<List<String>> result = new ArrayList<List<String>>();
		search(result, prefix2words, size, new ArrayList<String>());
		return result;
	}

	void search(List<List<String>> result, Map<String, List<String>> prefix2words, int size, List<String> square) {
		int index = square.size();

		if (index == size) {
			result.add(new ArrayList<String>(square));
			return;
		}

		StringBuilder prefixSb = new StringBuilder();
		for (int i = 0; i < index; i++) {
			prefixSb.append(square.get(i).charAt(index));
		}
		String prefix = prefixSb.toString();

		if (prefix2words.containsKey(prefix)) {
			for (String word : prefix2words.get(prefix)) {
				square.add(word);

				search(result, prefix2words, size, square);

				square.remove(square.size() - 1);
			}
		}
	}
}
