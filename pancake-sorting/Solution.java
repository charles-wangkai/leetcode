import java.util.ArrayList;
import java.util.List;

class Solution {
	public List<Integer> pancakeSort(int[] A) {
		List<Integer> result = new ArrayList<>();
		for (int i = A.length; i >= 1; --i) {
			int index = findIndex(A, i);

			if (index != i - 1) {
				result.add(index + 1);
				pancakeFlip(A, index + 1);

				result.add(i);
				pancakeFlip(A, i);
			}
		}

		return result;
	}

	int findIndex(int[] A, int target) {
		for (int i = 0;; ++i) {
			if (A[i] == target) {
				return i;
			}
		}
	}

	void pancakeFlip(int[] A, int k) {
		for (int i = 0, j = k - 1; i < j; ++i, --j) {
			int temp = A[i];
			A[i] = A[j];
			A[j] = temp;
		}
	}
}
