public class Solution {
	public int[] pourWater(int[] heights, int V, int K) {
		for (int i = 0; i < V; i++) {
			int minIndex = findMinIndex(heights, K, -1);
			if (minIndex != K) {
				heights[minIndex]++;
				continue;
			}

			minIndex = findMinIndex(heights, K, 1);
			heights[minIndex]++;
		}
		return heights;
	}

	int findMinIndex(int[] heights, int startIndex, int offset) {
		int minIndex = startIndex;
		for (int index = startIndex + offset; index >= 0 && index < heights.length
				&& heights[index] <= heights[index - offset]; index += offset) {
			if (heights[index] < heights[minIndex]) {
				minIndex = index;
			}
		}
		return minIndex;
	}
}
