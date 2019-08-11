import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MajorityChecker {
	private static final int MAX_TRY_NUM = 20;

	private Random random = new Random();
	private int[] arr;
	private Map<Integer, List<Integer>> valueToIndices = new HashMap<>();

	public MajorityChecker(int[] arr) {
		this.arr = arr;

		for (int i = 0; i < arr.length; i++) {
			if (!valueToIndices.containsKey(arr[i])) {
				valueToIndices.put(arr[i], new ArrayList<>());
			}

			valueToIndices.get(arr[i]).add(i);
		}
	}

	public int query(int left, int right, int threshold) {
		for (int i = 0; i < MAX_TRY_NUM; i++) {
			int value = arr[random.nextInt(right - left + 1) + left];

			if (computeCountInRange(left, right, value) >= threshold) {
				return value;
			}
		}

		return -1;
	}

	private int computeCountInRange(int left, int right, int value) {
		List<Integer> indices = valueToIndices.get(value);
		if (indices.get(0) > right || indices.get(indices.size() - 1) < left) {
			return 0;
		}

		int beginIndex = findBeginIndex(indices, left);
		int endIndex = findEndIndex(indices, right);

		return endIndex - beginIndex + 1;
	}

	private int findBeginIndex(List<Integer> indices, int left) {
		int result = -1;
		int lower = 0;
		int upper = indices.size() - 1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (indices.get(middle) >= left) {
				result = middle;

				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}

		return result;
	}

	private int findEndIndex(List<Integer> indices, int right) {
		int result = -1;
		int lower = 0;
		int upper = indices.size() - 1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (indices.get(middle) <= right) {
				result = middle;

				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}

		return result;
	}
}

// Your MajorityChecker object will be instantiated and called as such:
// MajorityChecker obj = new MajorityChecker(arr);
// int param_1 = obj.query(left,right,threshold);
