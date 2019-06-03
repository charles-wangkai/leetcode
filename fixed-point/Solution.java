public class Solution {
	public int fixedPoint(int[] A) {
		int result = -1;
		int lowerIndex = 0;
		int upperIndex = A.length - 1;
		while (lowerIndex <= upperIndex) {
			int middleIndex = (lowerIndex + upperIndex) / 2;

			if (A[middleIndex] > middleIndex) {
				upperIndex = middleIndex - 1;
			} else if (A[middleIndex] < middleIndex) {
				lowerIndex = middleIndex + 1;
			} else {
				result = middleIndex;

				upperIndex = middleIndex - 1;
			}
		}
		return result;
	}
}
