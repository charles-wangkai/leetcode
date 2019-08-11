import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int minSwaps(int[] data) {
		int count1 = (int) Arrays.stream(data).filter(x -> x == 1).count();
		if (count1 == 0) {
			return 0;
		}

		int sum = IntStream.range(0, count1).map(i -> data[i]).sum();
		int maxSum = sum;
		for (int i = count1; i < data.length; i++) {
			sum += data[i] - data[i - count1];
			maxSum = Math.max(maxSum, sum);
		}

		return count1 - maxSum;
	}
}
