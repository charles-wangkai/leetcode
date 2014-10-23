import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public int[] twoSum(int[] numbers, int target) {
		Map<Integer, Integer> number2index = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			number2index.put(numbers[i], i);
		}

		for (int i = 0;; i++) {
			Integer j = number2index.get(target - numbers[i]);
			if (j != null && j > i) {
				return new int[] { i + 1, j + 1 };
			}
		}
	}
}
