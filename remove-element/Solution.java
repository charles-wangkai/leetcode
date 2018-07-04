public class Solution {
	public int removeElement(int[] A, int elem) {
		int endIndex = A.length - 1;
		for (int i = 0; i <= endIndex; i++) {
			if (A[i] == elem) {
				A[i] = A[endIndex];
				A[endIndex] = elem;
				endIndex--;
				i--;
			}
		}
		return endIndex + 1;
	}
}
