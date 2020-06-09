public class Solution {
	public boolean isSubsequence(String s, String t) {
		int tFromIndex = 0;
		for (char sLetter : s.toCharArray()) {
			int index = t.indexOf(sLetter, tFromIndex);

			if (index == -1) {
				return false;
			}

			tFromIndex = index + 1;
		}

		return true;
	}
}
