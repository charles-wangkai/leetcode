import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public String frequencySort(String s) {
		Map<Character, Integer> letter2count = new HashMap<Character, Integer>();
		for (char letter : s.toCharArray()) {
			if (!letter2count.containsKey(letter)) {
				letter2count.put(letter, 0);
			}
			letter2count.put(letter, letter2count.get(letter) + 1);
		}

		SortedMap<Integer, List<Character>> count2letters = new TreeMap<Integer, List<Character>>(
				Comparator.reverseOrder());
		for (char letter : letter2count.keySet()) {
			int count = letter2count.get(letter);

			if (!count2letters.containsKey(count)) {
				count2letters.put(count, new ArrayList<Character>());
			}
			count2letters.get(count).add(letter);
		}

		StringBuilder sb = new StringBuilder();
		for (int count : count2letters.keySet()) {
			for (char letter : count2letters.get(count)) {
				for (int i = 0; i < count; i++) {
					sb.append(letter);
				}
			}
		}
		return sb.toString();
	}
}
