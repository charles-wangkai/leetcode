import java.util.Arrays;

class Solution {
	public int hIndex(int[] citations) {
		Arrays.sort(citations);

		for (int h = citations.length;; --h) {
			if ((h == 0 || citations[citations.length - h] >= h)) {
				return h;
			}
		}
	}
}
