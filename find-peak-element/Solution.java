public class Solution {
	public int findPeakElement(int[] num) {
		return findPeakElement(num, 0, num.length - 1);
	}

	int findPeakElement(int[] num, int begin, int end) {
		if (begin == end) {
			return begin;
		}
		int middle = (begin + end) / 2;
		if (isPeak(num, middle)) {
			return middle;
		}
		if (num[middle + 1] > num[middle]) {
			return findPeakElement(num, middle + 1, end);
		} else {
			return findPeakElement(num, begin, middle - 1);
		}
	}

	boolean isPeak(int[] num, int index) {
		return (index == 0 || num[index] > num[index - 1])
				&& (index == num.length - 1 || num[index] > num[index + 1]);
	}
}
