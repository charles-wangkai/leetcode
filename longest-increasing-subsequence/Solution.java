import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int lengthOfLIS(int[] nums) {
		List<Integer> tails = new ArrayList<Integer>();
		for (int number : nums) {
			if (tails.isEmpty() || number > tails.get(tails.size() - 1)) {
				tails.add(number);
			} else {
				tails.set(findFirstNotLessIndex(tails, number), number);
			}
		}
		return tails.size();
	}

	int findFirstNotLessIndex(List<Integer> numbers, int target) {
		int result = -1;
		int lower = 0;
		int upper = numbers.size() - 1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (numbers.get(middle) < target) {
				lower = middle + 1;
			} else {
				upper = middle - 1;
				result = middle;
			}
		}
		return result;
	}
}
