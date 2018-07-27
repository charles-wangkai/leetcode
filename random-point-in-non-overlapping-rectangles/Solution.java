import java.util.Arrays;
import java.util.Random;

public class Solution {
	private Random random = new Random();
	private int[][] rects;
	private int[] limits;
	private int total;

	public Solution(int[][] rects) {
		this.rects = rects;

		int limit = 0;
		limits = new int[rects.length];
		for (int i = 0; i < limits.length; i++) {
			int width = getWidth(rects[i]);
			int height = getHeight(rects[i]);

			limit += width * height;
			limits[i] = limit;
		}

		total = limit;
	}

	public int[] pick() {
		int number = random.nextInt(total) + 1;

		int rectIndex = Arrays.binarySearch(limits, number);
		if (rectIndex < 0) {
			rectIndex = -1 - rectIndex;
		}

		int[] rect = rects[rectIndex];
		int width = getWidth(rect);
		int height = getHeight(rect);
		int sequenceInRect = width * height - limits[rectIndex] + number - 1;

		int xOffset = sequenceInRect % width;
		int yOffset = sequenceInRect / width;
		return new int[] { rect[0] + xOffset, rect[1] + yOffset };
	}

	private int getWidth(int[] rect) {
		return rect[2] - rect[0] + 1;
	}

	private int getHeight(int[] rect) {
		return rect[3] - rect[1] + 1;
	}
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(rects);
// int[] param_1 = obj.pick();
