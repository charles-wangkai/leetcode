import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
	int minSquareNum;
	Map<Long, Integer> cache;

	public int tilingRectangle(int n, int m) {
		minSquareNum = Integer.MAX_VALUE;
		cache = new HashMap<>();

		search(m, new int[n], 0);

		return minSquareNum;
	}

	void search(int m, int[] heights, int squareNum) {
		if (squareNum == minSquareNum) {
			return;
		}

		if (Arrays.stream(heights).allMatch(height -> height == m)) {
			minSquareNum = squareNum;

			return;
		}

		long state = computeState(m, heights);
		if (cache.containsKey(state) && cache.get(state) <= squareNum) {
			return;
		}
		cache.put(state, squareNum);

		int minHeight = Arrays.stream(heights).min().getAsInt();
		int leftIndex = IntStream.range(0, heights.length).filter(i -> heights[i] == minHeight).findFirst().getAsInt();

		int maxRightIndex = leftIndex;
		while (maxRightIndex + 1 < heights.length && heights[maxRightIndex + 1] == minHeight
				&& maxRightIndex + 1 - leftIndex + 1 + minHeight <= m) {
			maxRightIndex++;
		}

		for (int rightIndex = maxRightIndex; rightIndex >= leftIndex; rightIndex--) {
			int squareSize = rightIndex - leftIndex + 1;

			for (int i = leftIndex; i <= rightIndex; i++) {
				heights[i] += squareSize;
			}

			search(m, heights, squareNum + 1);

			for (int i = leftIndex; i <= rightIndex; i++) {
				heights[i] -= squareSize;
			}
		}
	}

	long computeState(int m, int[] heights) {
		long result = 0;
		for (int height : heights) {
			result = result * (m + 1) + height;
		}

		return result;
	}
}
