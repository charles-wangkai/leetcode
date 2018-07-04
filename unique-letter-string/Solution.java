import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	static final int MODULUS = 1000000007;

	public int uniqueLetterString(String S) {
		Map<Character, List<Integer>> letter2indexes = new HashMap<>();
		for (int i = 0; i < S.length(); i++) {
			char letter = S.charAt(i);
			if (!letter2indexes.containsKey(letter)) {
				letter2indexes.put(letter, new ArrayList<>());
			}

			letter2indexes.get(letter).add(i);
		}

		int result = 0;
		for (List<Integer> indexes : letter2indexes.values()) {
			for (int i = 0; i < indexes.size(); i++) {
				int left = indexes.get(i) - ((i == 0) ? -1 : (indexes.get(i - 1)));
				int right = ((i == indexes.size() - 1) ? S.length() : indexes.get(i + 1)) - indexes.get(i);
				result = addMod(result, left * right);
			}
		}
		return result;
	}

	int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}
