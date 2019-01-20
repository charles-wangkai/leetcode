public class Solution {
	public int maxTurbulenceSize(int[] A) {
		int maxSize = 1;
		int size = 1;
		Boolean prevUpOrDown = null;
		for (int i = 1; i < A.length; i++) {
			if (prevUpOrDown != null && ((prevUpOrDown && A[i - 1] > A[i]) || (!prevUpOrDown && A[i - 1] < A[i]))) {
				prevUpOrDown = !prevUpOrDown;
				size++;
			} else {
				if (A[i - 1] > A[i]) {
					prevUpOrDown = false;
					size = 2;
				} else if (A[i - 1] < A[i]) {
					prevUpOrDown = true;
					size = 2;
				} else {
					prevUpOrDown = null;
				}
			}

			maxSize = Math.max(maxSize, size);
		}
		return maxSize;
	}
}
