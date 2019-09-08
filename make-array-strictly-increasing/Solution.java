import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int makeArrayIncreasing(int[] arr1, int[] arr2) {
		arr2 = Arrays.stream(arr2).distinct().sorted().toArray();

		Map<Integer, Integer> operationNumToLastValue = new HashMap<>();
		operationNumToLastValue.put(0, Integer.MIN_VALUE);
		for (int value : arr1) {
			Map<Integer, Integer> nextOperationNumToLastValue = new HashMap<>();
			for (int operationNum : operationNumToLastValue.keySet()) {
				int lastValue = operationNumToLastValue.get(operationNum);

				if (value > lastValue) {
					update(nextOperationNumToLastValue, operationNum, value);
				}

				Integer largerValue = findLargerValue(arr2, lastValue);
				if (largerValue != null) {
					update(nextOperationNumToLastValue, operationNum + 1, largerValue);
				}
			}

			operationNumToLastValue = nextOperationNumToLastValue;
		}

		return operationNumToLastValue.keySet().stream().mapToInt(x -> x).min().orElse(-1);
	}

	void update(Map<Integer, Integer> operationNumToLastValue, int operationNum, int lastValue) {
		operationNumToLastValue.put(operationNum,
				Math.min(operationNumToLastValue.getOrDefault(operationNum, Integer.MAX_VALUE), lastValue));
	}

	Integer findLargerValue(int[] arr2, int value) {
		int index = Arrays.binarySearch(arr2, value);

		int indexWithLargerValue;
		if (index >= 0) {
			indexWithLargerValue = index + 1;
		} else {
			indexWithLargerValue = -1 - index;
		}

		return (indexWithLargerValue == arr2.length) ? null : arr2[indexWithLargerValue];
	}
}
