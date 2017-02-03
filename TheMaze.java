public class TheMaze {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		int row = maze.length;
		int col = maze[0].length;

		return search(maze, destination, new boolean[row][col], start[0], start[1]);
	}

	boolean search(int[][] maze, int[] destination, boolean[][] visited, int r, int c) {
		if (r == destination[0] && c == destination[1]) {
			return true;
		}

		if (visited[r][c]) {
			return false;
		}

		visited[r][c] = true;

		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r;
			int nextC = c;

			while (isEmptySpace(maze, nextR + R_OFFSETS[i], nextC + C_OFFSETS[i])) {
				nextR += R_OFFSETS[i];
				nextC += C_OFFSETS[i];
			}

			if (search(maze, destination, visited, nextR, nextC)) {
				return true;
			}
		}
		return false;
	}

	boolean isEmptySpace(int[][] maze, int r, int c) {
		int row = maze.length;
		int col = maze[0].length;

		return r >= 0 && r < row && c >= 0 && c < col && maze[r][c] == 0;
	}
}
