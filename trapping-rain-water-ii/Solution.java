import java.util.PriorityQueue;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	int row;
	int col;

	public int trapRainWater(int[][] heightMap) {
		row = heightMap.length;
		if (row == 0) {
			return 0;
		}
		col = heightMap[0].length;
		if (col == 0) {
			return 0;
		}

		PriorityQueue<Point> pq = new PriorityQueue<Point>((p1, p2) -> heightMap[p2.r][p2.c] - heightMap[p1.r][p1.c]);
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				pq.offer(new Point(r, c));
			}
		}

		int water = 0;
		while (!pq.isEmpty()) {
			Point head = pq.poll();

			int wall = search(heightMap, new boolean[row][col], head.r, head.c, head.r, head.c);

			if (wall >= 0) {
				int addition = wall - heightMap[head.r][head.c];
				heightMap[head.r][head.c] += addition;
				water += addition;
			}
		}
		return water;
	}

	int search(int[][] heightMap, boolean[][] visited, int startR, int startC, int r, int c) {
		visited[r][c] = true;

		if (!(r == startR && c == startC) && heightMap[r][c] > heightMap[startR][startC]) {
			return heightMap[r][c];
		}

		int wall = Integer.MAX_VALUE;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i];
			int nextC = c + C_OFFSETS[i];

			if (!(nextR >= 0 && nextR < row && nextC >= 0 && nextC < col)) {
				return -1;
			}

			if (visited[nextR][nextC]) {
				continue;
			}

			int subWall = search(heightMap, visited, startR, startC, nextR, nextC);
			if (subWall < 0) {
				return subWall;
			}

			wall = Math.min(wall, subWall);
		}
		return wall;
	}
}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}