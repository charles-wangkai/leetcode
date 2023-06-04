import java.util.Arrays;

class Solution {
  public int findCircleNum(int[][] isConnected) {
    int[] parents = new int[isConnected.length];
    Arrays.fill(parents, -1);

    for (int i = 0; i < isConnected.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (isConnected[i][j] == 1) {
          int root1 = findRoot(parents, i);
          int root2 = findRoot(parents, j);
          if (root1 != root2) {
            parents[root2] = root1;
          }
        }
      }
    }

    return (int) Arrays.stream(parents).filter(parent -> parent == -1).count();
  }

  int findRoot(int[] parents, int node) {
    if (parents[node] == -1) {
      return node;
    }

    parents[node] = findRoot(parents, parents[node]);

    return parents[node];
  }
}
