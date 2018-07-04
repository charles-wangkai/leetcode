public class Solution {
	public boolean search(int[] A, int target) {
		return search(A, target, 0, A.length - 1);
	}

	boolean search(int[] A, int target, int lower, int upper) {
		if (lower > upper) {
			return false;
		}
		if (A[lower] < A[upper] && !(target >= A[lower] && target <= A[upper])) {
			return false;
		}
		int middle = (lower + upper) / 2;
		if (A[middle] == target) {
			return true;
		}
		return search(A, target, middle + 1, upper)
				|| search(A, target, lower, middle - 1);
	}
}
