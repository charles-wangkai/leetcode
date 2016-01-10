public class CountOfRangeSum {
	public int countRangeSum(int[] nums, int lower, int upper) {
		long[] sums = computeSums(nums);
		return countRangeSumNum(sums, 0, sums.length - 1, lower, upper);
	}

	long[] computeSums(int[] nums) {
		long[] sums = new long[nums.length];
		long sum = 0;
		for (int i = 0; i < sums.length; i++) {
			sum += nums[i];
			sums[i] = sum;
		}
		return sums;
	}

	int countRangeSumNum(long[] sums, int beginIndex, int endIndex, int lower, int upper) {
		if (beginIndex > endIndex) {
			return 0;
		}
		if (beginIndex == endIndex) {
			return (sums[beginIndex] >= lower && sums[beginIndex] <= upper) ? 1 : 0;
		}

		int middleIndex = (beginIndex + endIndex) / 2;
		int result = countRangeSumNum(sums, beginIndex, middleIndex, lower, upper)
				+ countRangeSumNum(sums, middleIndex + 1, endIndex, lower, upper);

		int lowerIndex = middleIndex + 1;
		int upperIndex = middleIndex + 1;
		for (int i = beginIndex; i <= middleIndex; i++) {
			while (lowerIndex <= endIndex && sums[lowerIndex] - sums[i] < lower) {
				lowerIndex++;
			}
			while (upperIndex <= endIndex && sums[upperIndex] - sums[i] <= upper) {
				upperIndex++;
			}
			result += upperIndex - lowerIndex;
		}

		long[] merged = new long[endIndex - beginIndex + 1];
		int leftIndex = beginIndex;
		int rightIndex = middleIndex + 1;
		for (int i = 0; i < merged.length; i++) {
			if (rightIndex == endIndex + 1 || (leftIndex != middleIndex + 1 && sums[leftIndex] <= sums[rightIndex])) {
				merged[i] = sums[leftIndex];
				leftIndex++;
			} else {
				merged[i] = sums[rightIndex];
				rightIndex++;
			}
		}
		for (int i = beginIndex; i <= endIndex; i++) {
			sums[i] = merged[i - beginIndex];
		}

		return result;
	}
}
