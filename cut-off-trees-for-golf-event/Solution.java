import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int cutOffTree(List<List<Integer>> forest) {
		int row = forest.size();
		int col = forest.get(0).size();

		SortedMap<Integer, Point> height2point = new TreeMap<Integer, Point>();
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				int value = forest.get(r).get(c);
				if (value > 1) {
					height2point.put(value, new Point(r, c));
				}
			}
		}

		int result = 0;
		Point prev = new Point(0, 0);
		for (Point current : height2point.values()) {
			int distance = computeDistance(forest, prev, current);
			if (distance < 0) {
				return -1;
			}
			result += distance;

			prev = current;
		}
		return result;
	}

	int computeDistance(List<List<Integer>> forest, Point start, Point end) {
		int row = forest.size();
		int col = forest.get(0).size();

		boolean[][] visited = new boolean[row][col];
		visited[start.r][start.c] = true;

		Queue<Element> queue = new LinkedList<Element>();
		queue.offer(new Element(start, 0));

		while (!queue.isEmpty()) {
			Element head = queue.poll();
			Point p = head.p;
			int distance = head.distance;

			if (p.equals(end)) {
				return distance;
			}

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int nextR = p.r + R_OFFSETS[i];
				int nextC = p.c + C_OFFSETS[i];

				if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && !visited[nextR][nextC]
						&& forest.get(nextR).get(nextC) != 0) {
					visited[nextR][nextC] = true;
					queue.offer(new Element(new Point(nextR, nextC), distance + 1));
				}
			}
		}
		return -1;
	}
}

class Element {
	Point p;
	int distance;

	Element(Point p, int distance) {
		this.p = p;
		this.distance = distance;
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
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return r == other.r && c == other.c;
	}
}