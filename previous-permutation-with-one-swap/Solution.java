public class Solution {
	public int[] prevPermOpt1(int[] A) {
		for (int i = A.length - 2; i >= 0; i--) {
			if (A[i] > A[i + 1]) {
				int index = i + 1;
				for (int j = i + 1; j < A.length; j++) {
					if (A[j] < A[i] && A[j] > A[index]) {
						index = j;
					}
				}

				swap(A, i, index);

				break;
			}
		}
		return A;
	}

	void swap(int[] A, int index1, int index2) {
		int temp = A[index1];
		A[index1] = A[index2];
		A[index2] = temp;
	}
}
