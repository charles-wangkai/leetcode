public class Solution {
	public int jump(int[] A) {
		int step = 0;
		int maxIndex = 0;
		int nextMaxIndex = -1;
		for (int i = 0; maxIndex < A.length - 1; i++) {
			if (i == maxIndex + 1) {
				maxIndex = nextMaxIndex;
				step++;
			}
			nextMaxIndex = Math.max(nextMaxIndex, i + A[i]);
		}
		return step;
	}
}
