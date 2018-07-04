import java.util.HashMap;
import java.util.Map;

public class Solution {
	Map<Pair, Boolean> history = new HashMap<Pair, Boolean>();
	int maxStarIndex = -1;

	public boolean isMatch(String s, String p) {
		return isMatch(s, 0, p, 0, countNonStars(p));
	}

	boolean isMatch(String s, int indexS, String p, int indexP,
			int nonStarLength) {
		if (nonStarLength > s.length() - indexS) {
			return false;
		}
		if (indexS == s.length()) {
			if (indexP == p.length()) {
				return true;
			}
			if (p.charAt(indexP) == '*') {
				return isMatch(s, indexS, p, indexP + 1, nonStarLength);
			}
			return false;
		}
		if (indexP == p.length()) {
			return false;
		}

		Pair pair = new Pair(indexS, indexP);
		if (history.containsKey(pair)) {
			return history.get(pair);
		}

		boolean result;
		char chP = p.charAt(indexP);
		if (chP == '*') {
			maxStarIndex = indexP;
			if (isMatch(s, indexS, p, indexP + 1, nonStarLength)) {
				result = true;
			} else if (maxStarIndex == indexP
					&& isMatch(s, indexS + 1, p, indexP, nonStarLength)) {
				result = true;
			} else {
				result = false;
			}
		} else if (chP != '?' && s.charAt(indexS) != chP) {
			result = false;
		} else {
			result = isMatch(s, indexS + 1, p, indexP + 1, nonStarLength - 1);
		}

		history.put(pair, result);
		return result;
	}

	int countNonStars(String p) {
		int nonStarLength = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) != '*') {
				nonStarLength++;
			}
		}
		return nonStarLength;
	}
}

class Pair {
	int a;
	int b;

	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode() {
		return a * b;
	}

	@Override
	public boolean equals(Object obj) {
		Pair other = (Pair) obj;
		return a == other.a && b == other.b;
	}
}