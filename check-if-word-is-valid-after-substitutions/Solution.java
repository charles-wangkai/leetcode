public class Solution {
	public boolean isValid(String S) {
		int countA = 0;
		int countB = 0;
		int countC = 0;

		for (char ch : S.toCharArray()) {
			if (ch == 'a') {
				countA++;
			} else if (ch == 'b') {
				countB++;

				if (countB > countA) {
					return false;
				}
			} else if (ch == 'c') {
				countC++;

				if (countC > countB) {
					return false;
				}
			}
		}

		return countA == countB && countB == countC;
	}
}
