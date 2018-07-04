import java.util.Arrays;
import java.util.Comparator;

public class Solution {
	public String largestNumber(int[] num) {
		if (allZeros(num)) {
			return "0";
		}

		Integer[] numbers = new Integer[num.length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = num[i];
		}
		Arrays.sort(numbers, new LargerComparator());

		StringBuilder sb = new StringBuilder();
		for (Integer number : numbers) {
			sb.append(number);
		}
		return sb.toString();
	}

	boolean allZeros(int[] num) {
		for (int oneNum : num) {
			if (oneNum != 0) {
				return false;
			}
		}
		return true;
	}
}

class LargerComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer num1, Integer num2) {
		return (int) Math.signum(concat(num2, num1) - concat(num1, num2));
	}

	long concat(int num1, int num2) {
		return Long.parseLong(num1 + "" + num2);
	}
}