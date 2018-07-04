public class Solution {
	public int findMin(int[] num) {
		return search(num, 0, num.length - 1);
	}

	int search(int[] num, int lower, int upper) {
		if (lower == upper || num[lower] < num[upper]) {
			return num[lower];
		}
		int middle = (lower + upper) / 2;
		return Math.min(search(num, lower, middle),
				search(num, middle + 1, upper));
	}
}
