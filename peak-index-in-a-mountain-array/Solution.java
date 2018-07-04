public class Solution {
	public int peakIndexInMountainArray(int[] A) {
		int lowerIndex = 0;
		int upperIndex = A.length - 1;
		while (true) {
			int middleIndex = (lowerIndex + upperIndex) / 2;
			if (middleIndex < A.length - 1 && A[middleIndex] < A[middleIndex + 1]) {
				lowerIndex = middleIndex + 1;
			} else if (middleIndex > 0 && A[middleIndex - 1] > A[middleIndex]) {
				upperIndex = middleIndex - 1;
			} else {
				return middleIndex;
			}
		}
	}
}
