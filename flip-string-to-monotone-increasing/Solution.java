public class Solution {
	public int minFlipsMonoIncr(String S) {
		int length = S.length();

		int leftOneNum = 0;
		int[] leftOneNums = new int[length];
		for (int i = 0; i < leftOneNums.length; i++) {
			if (S.charAt(i) == '1') {
				leftOneNum++;
			}
			leftOneNums[i] = leftOneNum;
		}

		int rightZeroNum = 0;
		int[] rightZeroNums = new int[length];
		for (int i = rightZeroNums.length - 1; i >= 0; i--) {
			if (S.charAt(i) == '0') {
				rightZeroNum++;
			}
			rightZeroNums[i] = rightZeroNum;
		}

		int result = Math.min(rightZeroNums[0], leftOneNums[length - 1]);
		for (int i = 0; i < length - 1; i++) {
			result = Math.min(result, leftOneNums[i] + rightZeroNums[i + 1]);
		}
		return result;
	}
}
