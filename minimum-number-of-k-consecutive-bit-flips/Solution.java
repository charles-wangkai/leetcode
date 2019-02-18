public class Solution {
	public int minKBitFlips(int[] A, int K) {
		int flipNum = 0;
		boolean[] flipped = new boolean[A.length];
		boolean currentFlipped = false;
		for (int i = 0; i < A.length; i++) {
			if (i >= K && flipped[i - K]) {
				currentFlipped = !currentFlipped;
			}

			if ((A[i] == 0 && !currentFlipped) || (A[i] == 1 && currentFlipped)) {
				if (i + K > A.length) {
					return -1;
				}

				flipped[i] = true;
				currentFlipped = !currentFlipped;
				flipNum++;
			}
		}
		return flipNum;
	}
}
