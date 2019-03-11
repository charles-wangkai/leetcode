public class Solution {
	public int minDominoRotations(int[] A, int[] B) {
		int result = Integer.MAX_VALUE;
		for (boolean topOrDown : new boolean[] { false, true }) {
			for (int target = 1; target <= 6; target++) {
				result = Math.min(result, findRotationNum(A, B, topOrDown, target));
			}
		}
		return (result == Integer.MAX_VALUE) ? -1 : result;
	}

	int findRotationNum(int[] A, int[] B, boolean topOrDown, int target) {
		int rotationNum = 0;
		for (int i = 0; i < A.length; i++) {
			if (topOrDown) {
				if (A[i] != target) {
					if (B[i] == target) {
						rotationNum++;
					} else {
						return Integer.MAX_VALUE;
					}
				}
			} else {
				if (B[i] != target) {
					if (A[i] == target) {
						rotationNum++;
					} else {
						return Integer.MAX_VALUE;
					}
				}
			}
		}
		return rotationNum;
	}
}
