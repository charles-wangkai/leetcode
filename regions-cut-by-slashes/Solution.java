import java.util.ArrayList;
import java.util.List;

class Solution {
  public int regionsBySlashes(String[] grid) {
    int n = grid.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n * n * 4];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }

    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (r != 0) {
          connect(
              adjLists, new Point(n, r, c, Direction.UP), new Point(n, r - 1, c, Direction.DOWN));
        }
        if (c != n - 1) {
          connect(
              adjLists,
              new Point(n, r, c, Direction.RIGHT),
              new Point(n, r, c + 1, Direction.LEFT));
        }
        if (r != n - 1) {
          connect(
              adjLists, new Point(n, r, c, Direction.DOWN), new Point(n, r + 1, c, Direction.UP));
        }
        if (c != 0) {
          connect(
              adjLists,
              new Point(n, r, c, Direction.LEFT),
              new Point(n, r, c - 1, Direction.RIGHT));
        }

        char square = grid[r].charAt(c);
        if (square == '/') {
          connect(adjLists, new Point(n, r, c, Direction.UP), new Point(n, r, c, Direction.LEFT));
          connect(
              adjLists, new Point(n, r, c, Direction.RIGHT), new Point(n, r, c, Direction.DOWN));
        } else if (square == '\\') {
          connect(adjLists, new Point(n, r, c, Direction.UP), new Point(n, r, c, Direction.RIGHT));
          connect(adjLists, new Point(n, r, c, Direction.DOWN), new Point(n, r, c, Direction.LEFT));
        } else {
          connect(adjLists, new Point(n, r, c, Direction.UP), new Point(n, r, c, Direction.RIGHT));
          connect(
              adjLists, new Point(n, r, c, Direction.RIGHT), new Point(n, r, c, Direction.DOWN));
          connect(adjLists, new Point(n, r, c, Direction.DOWN), new Point(n, r, c, Direction.LEFT));
          connect(adjLists, new Point(n, r, c, Direction.LEFT), new Point(n, r, c, Direction.UP));
        }
      }
    }

    int result = 0;
    boolean[] visited = new boolean[adjLists.length];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        dfs(adjLists, visited, i);
        ++result;
      }
    }

    return result;
  }

  void dfs(List<Integer>[] adjLists, boolean[] visited, int node) {
    visited[node] = true;

    for (int adj : adjLists[node]) {
      if (!visited[adj]) {
        dfs(adjLists, visited, adj);
      }
    }
  }

  void connect(List<Integer>[] adjLists, Point p1, Point p2) {
    adjLists[p1.getId()].add(p2.getId());
    adjLists[p2.getId()].add(p1.getId());
  }
}

enum Direction {
  UP,
  RIGHT,
  DOWN,
  LEFT
}

record Point(int n, int r, int c, Direction direction) {
  int getId() {
    return (r * n + c) * 4 + direction.ordinal();
  }
}
