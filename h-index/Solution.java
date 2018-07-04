import java.util.Arrays;

public class Solution {
	public int hIndex(int[] citations) {
		Arrays.sort(citations);

		for (int h = citations.length;; h--) {
			if ((h == 0 || citations[citations.length - h] >= h)
					&& (h == citations.length || citations[citations.length - h
							- 1] <= h)) {
				return h;
			}
		}
	}
}
