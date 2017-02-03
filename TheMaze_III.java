import java.util.PriorityQueue;

public class TheMaze_III {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };
	static final char[] DIRECTIONS = { 'u', 'r', 'd', 'l' };

	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		int row = maze.length;
		int col = maze[0].length;

		boolean[][] visited = new boolean[row][col];

		PriorityQueue<Element> pq = new PriorityQueue<Element>();
		pq.offer(new Element(ball[0], ball[1], 0, ""));

		while (!pq.isEmpty()) {
			Element head = pq.poll();

			if (visited[head.r][head.c]) {
				continue;
			}
			visited[head.r][head.c] = true;

			if (head.r == hole[0] && head.c == hole[1]) {
				return head.way;
			}

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int nextR = head.r;
				int nextC = head.c;
				int step = 0;

				while (!(nextR == hole[0] && nextC == hole[1])
						&& isEmptySpace(maze, nextR + R_OFFSETS[i], nextC + C_OFFSETS[i])) {
					nextR += R_OFFSETS[i];
					nextC += C_OFFSETS[i];
					step++;
				}

				pq.offer(new Element(nextR, nextC, head.distance + step, head.way + DIRECTIONS[i]));
			}
		}
		return "impossible";
	}

	boolean isEmptySpace(int[][] maze, int r, int c) {
		int row = maze.length;
		int col = maze[0].length;

		return r >= 0 && r < row && c >= 0 && c < col && maze[r][c] == 0;
	}
}

class Element implements Comparable<Element> {
	int r;
	int c;
	int distance;
	String way;

	Element(int r, int c, int distance, String way) {
		this.r = r;
		this.c = c;
		this.distance = distance;
		this.way = way;
	}

	@Override
	public int compareTo(Element other) {
		if (distance != other.distance) {
			return distance - other.distance;
		} else {
			return way.compareTo(other.way);
		}
	}
}