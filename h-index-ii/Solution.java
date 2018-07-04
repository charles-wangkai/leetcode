public class Solution {
	public int hIndex(int[] citations) {
		int result = -1;
		int lower = 0;
		int upper = citations.length;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;

			if (isValid(citations, middle)) {
				lower = middle + 1;
				result = middle;
			} else {
				upper = middle - 1;
			}
		}
		return result;
	}

	boolean isValid(int[] citations, int h) {
		return (h == 0 || citations[citations.length - h] >= h);
	}
}
