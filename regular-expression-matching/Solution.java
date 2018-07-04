public class Solution {
	public boolean isMatch(String s, String p) {
		return checkMatch(s, 0, p, 0);
	}

	boolean checkMatch(String s, int indexS, String p, int indexP) {
		if (indexS == s.length()) {
			if (indexP == p.length()) {
				return true;
			}
			return isNextZeroOrMoreMatch(p, indexP)
					&& checkMatch(s, indexS, p, indexP + 2);
		}

		if (indexP == p.length()) {
			return false;
		}

		boolean nextMatch = isCharMatch(s.charAt(indexS), p.charAt(indexP));
		if (isNextZeroOrMoreMatch(p, indexP)) {
			return checkMatch(s, indexS, p, indexP + 2)
					|| (nextMatch && checkMatch(s, indexS + 1, p, indexP));
		} else {
			return nextMatch && checkMatch(s, indexS + 1, p, indexP + 1);
		}
	}

	boolean isNextZeroOrMoreMatch(String p, int indexP) {
		return indexP + 1 < p.length() && p.charAt(indexP + 1) == '*';
	}

	boolean isCharMatch(char chS, char chP) {
		return chP == '.' || chS == chP;
	}
}
