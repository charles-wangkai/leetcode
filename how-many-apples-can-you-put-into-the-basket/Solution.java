import java.util.Arrays;

public class Solution {
	static final int LIMIT = 5000;

	public int maxNumberOfApples(int[] arr) {
		Arrays.sort(arr);

		int result = 0;
		int index = 0;
		int sum = 0;
		while (index < arr.length && sum + arr[index] <= LIMIT) {
			sum += arr[index];
			index++;
			result++;
		}

		return result;
	}
}
