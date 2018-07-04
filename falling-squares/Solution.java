import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> fallingSquares(int[][] positions) {
		int maxHeight = 0;
		List<Integer> maxHeights = new ArrayList<Integer>();
		List<Square> squares = new ArrayList<Square>();
		for (int[] position : positions) {
			int left = position[0];
			int size = position[1];
			int right = left + size - 1;
			int height = size;

			for (Square square : squares) {
				if (square.right < left || square.left > right) {
					continue;
				}

				height = Math.max(height, square.height + size);
			}
			squares.add(new Square(left, right, height));

			maxHeight = Math.max(maxHeight, height);
			maxHeights.add(maxHeight);
		}
		return maxHeights;
	}
}

class Square {
	int left;
	int right;
	int height;

	Square(int left, int right, int height) {
		this.left = left;
		this.right = right;
		this.height = height;
	}
}