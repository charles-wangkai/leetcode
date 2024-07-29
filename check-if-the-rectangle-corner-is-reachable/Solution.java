import java.util.Arrays;

class Solution {
  public boolean canReachCorner(int X, int Y, int[][] circles) {
    int[] parents = new int[circles.length + 4];
    Arrays.fill(parents, -1);

    for (int i = 0; i < circles.length; ++i) {
      if (circles[i][2] >= circles[i][0]) {
        union(parents, i, circles.length);
      }
      if (circles[i][2] >= Y - circles[i][1]) {
        union(parents, i, circles.length + 1);
      }
      if (circles[i][2] >= X - circles[i][0]) {
        union(parents, i, circles.length + 2);
      }
      if (circles[i][2] >= circles[i][1]) {
        union(parents, i, circles.length + 3);
      }
    }

    for (int i = 0; i < circles.length; ++i) {
      for (int j = i + 1; j < circles.length; ++j) {
        if ((long) (circles[i][0] - circles[j][0]) * (circles[i][0] - circles[j][0])
                + (long) (circles[i][1] - circles[j][1]) * (circles[i][1] - circles[j][1])
            <= (long) (circles[i][2] + circles[j][2]) * (circles[i][2] + circles[j][2])) {
          union(parents, i, j);
        }
      }
    }

    return !(findRoot(parents, circles.length) == findRoot(parents, circles.length + 2)
        || findRoot(parents, circles.length) == findRoot(parents, circles.length + 3)
        || findRoot(parents, circles.length + 1) == findRoot(parents, circles.length + 2)
        || findRoot(parents, circles.length + 1) == findRoot(parents, circles.length + 3));
  }

  void union(int[] parents, int node1, int node2) {
    int root1 = findRoot(parents, node1);
    int root2 = findRoot(parents, node2);
    if (root1 != root2) {
      parents[root2] = root1;
    }
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}