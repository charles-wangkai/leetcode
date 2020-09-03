class Solution {
	public boolean repeatedSubstringPattern(String s) {
		for (int patternLength = 1; patternLength * 2 <= s.length(); ++patternLength) {
			if (isRepeat(s, patternLength)) {
				return true;
			}
		}

		return false;
	}

	boolean isRepeat(String str, int patternLength) {
		if (str.length() % patternLength != 0) {
			return false;
		}

		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) != str.charAt(i % patternLength)) {
				return false;
			}
		}

		return true;
	}
}
