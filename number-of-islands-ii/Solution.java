import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {
  static final int[] X_OFFSETS = {0, 1, 0, -1};
  static final int[] Y_OFFSETS = {1, 0, -1, 0};

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    Point[][] parents = new Point[m][n];

    int islandNum = 0;
    List<Integer> islandNums = new ArrayList<>();
    for (int[] position : positions) {
      int x = position[0];
      int y = position[1];
      parents[x][y] = new Point(x, y);
      islandNum++;

      for (int i = 0; i < X_OFFSETS.length; i++) {
        int adjX = x + X_OFFSETS[i];
        int adjY = y + Y_OFFSETS[i];
        if (adjX >= 0 && adjX < m && adjY >= 0 && adjY < n && parents[adjX][adjY] != null) {
          Point adjRoot = findRoot(parents, adjX, adjY);
          if (!adjRoot.equals(parents[x][y])) {
            parents[adjRoot.x][adjRoot.y].x = x;
            parents[adjRoot.x][adjRoot.y].y = y;
            islandNum--;
          }
        }
      }

      islandNums.add(islandNum);
    }
    return islandNums;
  }

  Point findRoot(Point[][] parents, int x, int y) {
    Point root = new Point(x, y);
    while (!root.equals(parents[root.x][root.y])) {
      root = parents[root.x][root.y];
    }

    for (Point node = new Point(x, y); !node.equals(root); node = parents[root.x][root.y]) {
      parents[node.x][node.y].x = root.x;
      parents[node.x][node.y].y = root.y;
    }

    return root;
  }
}

class Point {
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    Point other = (Point) obj;
    return x == other.x && y == other.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
