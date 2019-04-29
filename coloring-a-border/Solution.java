import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
		int row = grid.length;
		int col = grid[0].length;

		List<Point> borders = new ArrayList<>();
		search(borders, grid, new boolean[row][col], r0, c0);

		for (Point border : borders) {
			grid[border.r][border.c] = color;
		}
		return grid;
	}

	void search(List<Point> borders, int[][] grid, boolean[][] visited, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;

		visited[r][c] = true;

		if (isBorder(grid, r, c)) {
			borders.add(new Point(r, c));
		}

		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjR = r + R_OFFSETS[i];
			int adjC = c + C_OFFSETS[i];

			if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && !visited[adjR][adjC]
					&& grid[adjR][adjC] == grid[r][c]) {
				search(borders, grid, visited, adjR, adjC);
			}
		}
	}

	boolean isBorder(int[][] grid, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;

		return IntStream.range(0, R_OFFSETS.length).anyMatch(i -> {
			int adjR = r + R_OFFSETS[i];
			int adjC = c + C_OFFSETS[i];

			return !(adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] == grid[r][c]);
		});
	}
}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(r, c);
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return r == other.r && c == other.c;
	}
}