public class Solution {
	public void sortColors(int[] A) {
		int index0 = -1;
		int index2 = A.length;
		for (int i = 0; i < index2; ++i) {
			if (A[i] == 0) {
				++index0;
				swap(A, i, index0);
			} else if (A[i] == 2) {
				--index2;
				swap(A, i, index2);
				--i;
			}
		}
	}

	void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
}
