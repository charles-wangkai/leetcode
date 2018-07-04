public class Solution {
	public int removeDuplicates(int[] A) {
		int length = 0;
		for (int i = 0; i < A.length; i++) {
			if (length == 0 || A[i] != A[length - 1]) {
				A[length] = A[i];
				length++;
			}
		}
		return length;
	}
}
