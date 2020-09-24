import java.util.Arrays;

class Solution {
	public char findTheDifference(String s, String t) {
		return findDiffLetter(buildKey(s), buildKey(t));
	}

	char[] buildKey(String x) {
		char[] key = x.toCharArray();
		Arrays.sort(key);

		return key;
	}

	char findDiffLetter(char[] keyS, char[] keyT) {
		for (int i = 0;; ++i) {
			if (i == keyS.length || keyT[i] != keyS[i]) {
				return keyT[i];
			}
		}
	}
}
