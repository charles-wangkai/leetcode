import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {
	public List<String> generatePalindromes(String s) {
		Map<Character, Integer> ch2count = new HashMap<Character, Integer>();
		for (char ch : s.toCharArray()) {
			addMapValue(ch2count, ch, 1);
		}

		boolean hasOddCount = false;
		for (int count : ch2count.values()) {
			if (count % 2 != 0) {
				if (hasOddCount) {
					return new ArrayList<String>();
				}
				hasOddCount = true;
			}
		}

		return search(ch2count);
	}

	List<String> search(Map<Character, Integer> ch2count) {
		List<String> result = new ArrayList<String>();
		if (ch2count.size() <= 1) {
			String palindrome = "";
			for (Entry<Character, Integer> entry : ch2count.entrySet()) {
				for (int i = 0; i < entry.getValue(); i++) {
					palindrome += entry.getKey();
				}
			}
			result.add(palindrome);
		} else {
			Set<Entry<Character, Integer>> entries = new HashSet<Entry<Character, Integer>>(
					ch2count.entrySet());
			for (Entry<Character, Integer> entry : entries) {
				char ch = entry.getKey();
				int count = entry.getValue();

				if (count >= 2) {
					addMapValue(ch2count, ch, -2);

					List<String> subResult = search(ch2count);
					for (String oneSubResult : subResult) {
						result.add(ch + oneSubResult + ch);
					}

					addMapValue(ch2count, ch, 2);
				}
			}
		}
		return result;
	}

	void addMapValue(Map<Character, Integer> ch2count, char ch, int delta) {
		if (!ch2count.containsKey(ch)) {
			ch2count.put(ch, 0);
		}

		int value = ch2count.get(ch) + delta;
		if (value == 0) {
			ch2count.remove(ch);
		} else {
			ch2count.put(ch, value);
		}
	}
}
