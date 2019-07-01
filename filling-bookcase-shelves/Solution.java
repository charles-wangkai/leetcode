public class Solution {
	public int minHeightShelves(int[][] books, int shelf_width) {
		int[] minHeights = new int[books.length + 1];
		for (int i = 1; i < minHeights.length; i++) {
			minHeights[i] = minHeights[i - 1] + books[i - 1][1];

			int widthSum = books[i - 1][0];
			int maxHeight = books[i - 1][1];
			for (int j = i - 2; j >= 0; j--) {
				widthSum += books[j][0];
				if (widthSum > shelf_width) {
					break;
				}

				maxHeight = Math.max(maxHeight, books[j][1]);

				minHeights[i] = Math.min(minHeights[i], minHeights[j] + maxHeight);
			}
		}

		return minHeights[books.length];
	}
}
