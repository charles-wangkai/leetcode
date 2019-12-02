import java.util.Arrays;

public class Solution {
	public int palindromePartition(String s, int k) {
		int[][] costs = buildCosts(s);

		int[][] changeNums = new int[s.length() + 1][k + 1];
		for (int i = 0; i < changeNums.length; i++) {
			Arrays.fill(changeNums[i], Integer.MAX_VALUE);
		}
		changeNums[0][0] = 0;

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= k; j++) {
				for (int p = 0; p < i; p++) {
					if (changeNums[p][j - 1] != Integer.MAX_VALUE) {
						changeNums[i][j] = Math.min(changeNums[i][j], changeNums[p][j - 1] + costs[p][i - 1]);
					}
				}
			}
		}

		return changeNums[s.length()][k];
	}

	int[][] buildCosts(String s) {
		int length = s.length();
		int[][] costs = new int[length][length];
		for (int i = 1; i <= length; i++) {
			for (int beginIndex = 0; beginIndex + i <= length; beginIndex++) {
				int endIndex = beginIndex + i - 1;

				costs[beginIndex][endIndex] = ((endIndex - beginIndex >= 2) ? costs[beginIndex + 1][endIndex - 1] : 0)
						+ (s.charAt(beginIndex) == s.charAt(endIndex) ? 0 : 1);
			}
		}

		return costs;
	}
}
