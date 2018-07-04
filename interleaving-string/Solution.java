public class Solution {
	public boolean isInterleave(String s1, String s2, String s3) {
		int s1Len = s1.length();
		int s2Len = s2.length();
		int s3Len = s3.length();
		if (s1Len + s2Len != s3Len) {
			return false;
		}
		boolean[][] interleaves = new boolean[s1Len + 1][s2Len + 1];
		for (int i = 0; i <= s1Len; i++) {
			for (int j = 0; j <= s2Len; j++) {
				int totalLength = i + j;
				if (totalLength == 0) {
					interleaves[i][j] = true;
				} else {
					char ch = s3.charAt(totalLength - 1);
					interleaves[i][j] = (i > 0 && ch == s1.charAt(i - 1) && interleaves[i - 1][j])
							|| (j > 0 && ch == s2.charAt(j - 1) && interleaves[i][j - 1]);
				}
			}
		}
		return interleaves[s1Len][s2Len];
	}
}
