import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int subarraysWithKDistinct(int[] A, int K) {
		Map<Integer, Queue<Integer>> valueToIndices = new HashMap<>();

		int minIndex = -1;
		int endIndex = -1;
		int result = 0;
		for (int beginIndex = 0; beginIndex < A.length; beginIndex++) {
			if (beginIndex != 0) {
				removeValueToIndices(valueToIndices, A[beginIndex - 1]);

				if (valueToIndices.containsKey(A[beginIndex - 1])) {
					minIndex = Math.max(minIndex, valueToIndices.get(A[beginIndex - 1]).peek());
				} else {
					minIndex = -1;
				}
			}

			while (endIndex + 1 < A.length
					&& (valueToIndices.size() < K || valueToIndices.containsKey(A[endIndex + 1]))) {
				endIndex++;

				addValueToIndices(valueToIndices, A[endIndex], endIndex);

				if (valueToIndices.size() == K && minIndex < 0) {
					minIndex = endIndex;
				}
			}

			if (minIndex < 0) {
				break;
			}

			result += endIndex - minIndex + 1;
		}
		return result;
	}

	void addValueToIndices(Map<Integer, Queue<Integer>> valueToIndices, int value, int index) {
		if (!valueToIndices.containsKey(value)) {
			valueToIndices.put(value, new LinkedList<>());
		}

		valueToIndices.get(value).offer(index);
	}

	void removeValueToIndices(Map<Integer, Queue<Integer>> valueToIndices, int value) {
		valueToIndices.get(value).poll();

		if (valueToIndices.get(value).isEmpty()) {
			valueToIndices.remove(value);
		}
	}
}
