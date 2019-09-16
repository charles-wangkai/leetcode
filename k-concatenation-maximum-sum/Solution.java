import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	static final int MODULUS = 1_000_000_007;

	public int kConcatenationMaxSum(int[] arr, int k) {
		long result;
		if (k == 1) {
			result = computeMaxSubArraySum(arr);
		} else {
			int maxPrefixSum = computeMaxPrefixSum(arr);
			int maxSuffixSum = computeMaxSuffixSum(arr);

			result = Math.max(computeMaxSubArraySum(repeatTwice(arr)),
					maxPrefixSum + Arrays.stream(arr).sum() * (k - 2L) + maxSuffixSum);
		}

		return (int) (result % MODULUS);
	}

	int[] repeatTwice(int[] arr) {
		return IntStream.range(0, arr.length * 2).map(i -> arr[i % arr.length]).toArray();
	}

	int computeMaxPrefixSum(int[] arr) {
		int result = 0;
		int prefixSum = 0;
		for (int ai : arr) {
			prefixSum += ai;
			result = Math.max(result, prefixSum);
		}

		return result;
	}

	int computeMaxSuffixSum(int[] arr) {
		int result = 0;
		int suffixSum = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			suffixSum += arr[i];
			result = Math.max(result, suffixSum);
		}

		return result;
	}

	int computeMaxSubArraySum(int[] a) {
		int result = 0;
		int sum = 0;
		for (int ai : a) {
			sum = Math.max(0, sum + ai);
			result = Math.max(result, sum);
		}

		return result;
	}
}
