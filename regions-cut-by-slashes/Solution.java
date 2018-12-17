import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int regionsBySlashes(String[] grid) {
		int size = grid.length;

		@SuppressWarnings("unchecked")
		List<Integer>[] adjacentLists = new List[size * size * 4];
		for (int i = 0; i < adjacentLists.length; i++) {
			adjacentLists[i] = new ArrayList<>();
		}

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (r != 0) {
					connect(adjacentLists, new Location(size, r, c, Direction.UP),
							new Location(size, r - 1, c, Direction.DOWN));
				}
				if (c != size - 1) {
					connect(adjacentLists, new Location(size, r, c, Direction.RIGHT),
							new Location(size, r, c + 1, Direction.LEFT));
				}
				if (r != size - 1) {
					connect(adjacentLists, new Location(size, r, c, Direction.DOWN),
							new Location(size, r + 1, c, Direction.UP));
				}
				if (c != 0) {
					connect(adjacentLists, new Location(size, r, c, Direction.LEFT),
							new Location(size, r, c - 1, Direction.RIGHT));
				}

				char cell = grid[r].charAt(c);
				if (cell == '/') {
					connect(adjacentLists, new Location(size, r, c, Direction.UP),
							new Location(size, r, c, Direction.LEFT));
					connect(adjacentLists, new Location(size, r, c, Direction.RIGHT),
							new Location(size, r, c, Direction.DOWN));
				} else if (cell == '\\') {
					connect(adjacentLists, new Location(size, r, c, Direction.UP),
							new Location(size, r, c, Direction.RIGHT));
					connect(adjacentLists, new Location(size, r, c, Direction.DOWN),
							new Location(size, r, c, Direction.LEFT));
				} else {
					connect(adjacentLists, new Location(size, r, c, Direction.UP),
							new Location(size, r, c, Direction.RIGHT));
					connect(adjacentLists, new Location(size, r, c, Direction.RIGHT),
							new Location(size, r, c, Direction.DOWN));
					connect(adjacentLists, new Location(size, r, c, Direction.DOWN),
							new Location(size, r, c, Direction.LEFT));
					connect(adjacentLists, new Location(size, r, c, Direction.LEFT),
							new Location(size, r, c, Direction.UP));
				}
			}
		}

		int regionNum = 0;
		boolean[] visited = new boolean[adjacentLists.length];
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				dfs(adjacentLists, visited, i);

				regionNum++;
			}
		}
		return regionNum;
	}

	void dfs(List<Integer>[] adjacentLists, boolean[] visited, int node) {
		visited[node] = true;

		for (int adjacent : adjacentLists[node]) {
			if (!visited[adjacent]) {
				dfs(adjacentLists, visited, adjacent);
			}
		}
	}

	void connect(List<Integer>[] adjacentLists, Location loc1, Location loc2) {
		int id1 = loc1.getId();
		int id2 = loc2.getId();

		adjacentLists[id1].add(id2);
		adjacentLists[id2].add(id1);
	}
}

enum Direction {
	UP, RIGHT, DOWN, LEFT
}

class Location {
	int size;
	int r;
	int c;
	Direction direction;

	Location(int size, int r, int c, Direction direction) {
		this.size = size;
		this.r = r;
		this.c = c;
		this.direction = direction;
	}

	int getId() {
		return (r * size + c) * 4 + direction.ordinal();
	}
}