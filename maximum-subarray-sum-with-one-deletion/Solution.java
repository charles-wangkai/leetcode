import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int maximumSum(int[] arr) {
		if (Arrays.stream(arr).allMatch(x -> x < 0)) {
			return Arrays.stream(arr).max().getAsInt();
		}

		int[] leftMaxSums = buildLeftMaxSums(arr);
		int[] rightMaxSums = buildRightMaxSums(arr);

		return Math.max(computeMaxSubArraySum(arr),
				IntStream.range(0, arr.length).map(i -> leftMaxSums[i] + rightMaxSums[i]).max().getAsInt());
	}

	int computeMaxSubArraySum(int[] arr) {
		int maxSum = 0;
		int sum = 0;
		for (int x : arr) {
			sum = Math.max(0, sum + x);
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}

	int[] buildLeftMaxSums(int[] arr) {
		int[] leftMaxSums = new int[arr.length];
		int sum = 0;
		for (int i = 0; i < leftMaxSums.length; i++) {
			leftMaxSums[i] = sum;
			sum = Math.max(0, sum + arr[i]);
		}

		return leftMaxSums;
	}

	int[] buildRightMaxSums(int[] arr) {
		int[] rightMaxSums = new int[arr.length];
		int sum = 0;
		for (int i = rightMaxSums.length - 1; i >= 0; i--) {
			rightMaxSums[i] = sum;
			sum = Math.max(0, sum + arr[i]);
		}

		return rightMaxSums;
	}
}
