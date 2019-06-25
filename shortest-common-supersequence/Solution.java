public class Solution {
	public String shortestCommonSupersequence(String str1, String str2) {
		int length1 = str1.length();
		int length2 = str2.length();

		int[][] minLengths = new int[length1 + 1][length2 + 1];
		for (int i = 0; i <= length1; i++) {
			for (int j = 0; j <= length2; j++) {
				if (i == 0) {
					minLengths[i][j] = j;
				} else if (j == 0) {
					minLengths[i][j] = i;
				} else {
					minLengths[i][j] = Math.min(Math.min(minLengths[i - 1][j], minLengths[i][j - 1]) + 1,
							minLengths[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 1 : 2));
				}
			}
		}

		StringBuilder result = new StringBuilder();
		int r = length1;
		int c = length2;
		while (r != 0 || c != 0) {
			if (r != 0 && minLengths[r][c] == minLengths[r - 1][c] + 1) {
				result.append(str1.charAt(r - 1));

				r--;
			} else if (c != 0 && minLengths[r][c] == minLengths[r][c - 1] + 1) {
				result.append(str2.charAt(c - 1));

				c--;
			} else {
				result.append(str1.charAt(r - 1));
				if (str1.charAt(r - 1) != str2.charAt(c - 1)) {
					result.append(str2.charAt(c - 1));
				}

				r--;
				c--;
			}
		}

		return result.reverse().toString();
	}
}
