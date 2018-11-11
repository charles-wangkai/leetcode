import java.util.HashMap;
import java.util.Map;

public class Solution {
	static final int MOD_DIVISOR = 1_000_000_007;

	public int distinctSubseqII(String S) {
		Map<Character, Integer> endLetterToCount = new HashMap<>();
		for (char letter : S.toCharArray()) {
			endLetterToCount.put(letter, addMod(computeCountSum(endLetterToCount), 1));
		}
		return computeCountSum(endLetterToCount);
	}

	int computeCountSum(Map<Character, Integer> endLetterToCount) {
		return endLetterToCount.values().stream().reduce(0, this::addMod);
	}

	int addMod(int x, int y) {
		return (x + y) % MOD_DIVISOR;
	}
}
