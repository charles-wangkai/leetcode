import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<Integer> countSteppingNumbers(int low, int high) {
		List<Integer> result = new ArrayList<>();

		if (low == 0) {
			result.add(0);
		}

		for (int digit = 1; digit <= 9; digit++) {
			search(result, low, high, digit);
		}

		Collections.sort(result);

		return result;
	}

	void search(List<Integer> result, int low, int high, long number) {
		if (number > high) {
			return;
		}

		if (number >= low) {
			result.add((int) number);
		}

		int lastDigit = (int) (number % 10);
		if (lastDigit >= 1) {
			search(result, low, high, number * 10 + (lastDigit - 1));
		}
		if (lastDigit <= 8) {
			search(result, low, high, number * 10 + (lastDigit + 1));
		}
	}
}
