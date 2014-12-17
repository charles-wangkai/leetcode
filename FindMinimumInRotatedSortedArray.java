public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] num) {
		int lower = 0;
		int upper = num.length - 1;
		while (true) {
			if (num[lower] <= num[upper]) {
				return num[lower];
			}
			int middle = (lower + upper) / 2;
			if (num[middle] >= num[lower]) {
				lower = middle + 1;
			} else {
				upper = middle;
			}
		}
	}
}
