public class Solution {
	public int countBinarySubstrings(String s) {
		int result = 0;
		int prevCount = 0;
		int currCount = 0;
		char target = '0';
		for (int i = 0; i < s.length(); i++) {
			char current = s.charAt(i);
			if (current != target) {
				prevCount = currCount;
				currCount = 0;
				target = (char) ('0' + '1' - target);
			}

			currCount++;
			if (currCount <= prevCount) {
				result++;
			}
		}
		return result;
	}
}
