public class FloodFill {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		fill(image, new boolean[image.length][image[0].length], sr, sc, image[sr][sc], newColor);
		return image;
	}

	void fill(int[][] image, boolean[][] visited, int r, int c, int oldColor, int newColor) {
		int row = image.length;
		int col = image[0].length;

		image[r][c] = newColor;
		visited[r][c] = true;

		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i];
			int nextC = c + C_OFFSETS[i];

			if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && !visited[nextR][nextC]
					&& image[nextR][nextC] == oldColor) {
				fill(image, visited, nextR, nextC, oldColor, newColor);
			}
		}
	}
}
