import java.util.LinkedList;
import java.util.List;

public class FindKClosestElements {
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
		int leftIndex = findLeftIndex(arr, x);
		int rightIndex = leftIndex + 1;

		LinkedList<Integer> result = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {
			if (rightIndex == arr.size() || (leftIndex >= 0 && x - arr.get(leftIndex) <= arr.get(rightIndex) - x)) {
				result.addFirst(arr.get(leftIndex));
				leftIndex--;
			} else {
				result.add(arr.get(rightIndex));
				rightIndex++;
			}
		}
		return result;
	}

	int findLeftIndex(List<Integer> arr, int x) {
		if (x < arr.get(0)) {
			return -1;
		}

		int lower = 0;
		int upper = arr.size() - 1;
		while (lower < upper) {
			int middle = (lower + upper + 1) / 2;
			if (arr.get(middle) <= x) {
				lower = middle;
			} else {
				upper = middle - 1;
			}
		}
		return lower;
	}
}
