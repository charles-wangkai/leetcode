public class Solution {
	public void merge(int A[], int m, int B[], int n) {
		int indexA = m - 1;
		int indexB = n - 1;
		for (int i = m + n - 1; i >= 0; i--) {
			if (indexA >= 0 && (indexB < 0 || A[indexA] >= B[indexB])) {
				A[i] = A[indexA];
				indexA--;
			} else {
				A[i] = B[indexB];
				indexB--;
			}
		}
	}
}
