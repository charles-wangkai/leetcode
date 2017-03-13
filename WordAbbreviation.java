import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordAbbreviation {
	public List<String> wordsAbbreviation(List<String> dict) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < dict.size(); i++) {
			result.add(null);
		}

		Map<LastAndLength, List<Integer>> lastAndLength2indices = new HashMap<LastAndLength, List<Integer>>();
		for (int i = 0; i < dict.size(); i++) {
			String word = dict.get(i);

			LastAndLength lastAndLength = new LastAndLength(word.charAt(word.length() - 1), word.length());

			if (!lastAndLength2indices.containsKey(lastAndLength)) {
				lastAndLength2indices.put(lastAndLength, new ArrayList<Integer>());
			}
			lastAndLength2indices.get(lastAndLength).add(i);
		}
		lastAndLength2indices.values().forEach(indices -> divide(result, dict, 0, indices));

		return result;
	}

	void divide(List<String> result, List<String> dict, int prefixLength, List<Integer> indexList) {
		if (prefixLength > 0 && indexList.size() == 1) {
			int index = indexList.get(0);
			result.set(index, toAbbreviation(dict.get(index), prefixLength));

			return;
		}

		Map<Character, List<Integer>> ch2indices = new HashMap<Character, List<Integer>>();
		for (int index : indexList) {
			char ch = dict.get(index).charAt(prefixLength);

			if (!ch2indices.containsKey(ch)) {
				ch2indices.put(ch, new ArrayList<Integer>());
			}
			ch2indices.get(ch).add(index);
		}
		ch2indices.values().forEach(indices -> divide(result, dict, prefixLength + 1, indices));
	}

	String toAbbreviation(String word, int prefixLength) {
		if (prefixLength >= word.length() - 2) {
			return word;
		}

		return String.format("%s%d%c", word.substring(0, prefixLength), word.length() - prefixLength - 1,
				word.charAt(word.length() - 1));
	}
}

class LastAndLength {
	char last;
	int length;

	LastAndLength(char last, int length) {
		this.last = last;
		this.length = length;
	}

	@Override
	public int hashCode() {
		return last * length;
	}

	@Override
	public boolean equals(Object obj) {
		LastAndLength other = (LastAndLength) obj;
		return last == other.last && length == other.length;
	}
}