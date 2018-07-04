public class Solution {
	static final int[] X_OFFSETS = { 0, 1, 0, -1 };
	static final int[] Y_OFFSETS = { 1, 0, -1, 0 };

	int blackMinX;
	int blackMaxX;
	int blackMinY;
	int blackMaxY;

	public int minArea(char[][] image, int x, int y) {
		int row = image.length;
		if (row == 0) {
			return 0;
		}
		int col = image[0].length;

		boolean[][] visited = new boolean[row][col];
		blackMinX = Integer.MAX_VALUE;
		blackMaxX = Integer.MIN_VALUE;
		blackMinY = Integer.MAX_VALUE;
		blackMaxY = Integer.MIN_VALUE;
		search(image, visited, x, y);
		return (blackMaxX - blackMinX + 1) * (blackMaxY - blackMinY + 1);
	}

	void search(char[][] image, boolean[][] visited, int x, int y) {
		visited[x][y] = true;

		if (image[x][y] != '1') {
			return;
		}

		blackMinX = Math.min(blackMinX, x);
		blackMaxX = Math.max(blackMaxX, x);
		blackMinY = Math.min(blackMinY, y);
		blackMaxY = Math.max(blackMaxY, y);

		int row = image.length;
		int col = image[0].length;

		for (int i = 0; i < X_OFFSETS.length; i++) {
			int adjX = x + X_OFFSETS[i];
			int adjY = y + Y_OFFSETS[i];
			if (adjX >= 0 && adjX < row && adjY >= 0 && adjY < col && !visited[adjX][adjY]) {
				search(image, visited, adjX, adjY);
			}
		}
	}
}
