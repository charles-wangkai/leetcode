import java.util.Arrays;

class Solution {
  public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
    int[] parents = new int[n];
    Arrays.fill(parents, -1);

    boolean[] result = new boolean[requests.length];
    for (int i = 0; i < result.length; ++i) {
      int root1 = findRoot(parents, requests[i][0]);
      int root2 = findRoot(parents, requests[i][1]);
      if (root1 == root2) {
        result[i] = true;
      } else if (check(parents, restrictions, root1, root2)) {
        parents[root2] = root1;
        result[i] = true;
      }
    }

    return result;
  }

  boolean check(int[] parents, int[][] restrictions, int root1, int root2) {
    return Arrays.stream(restrictions)
        .allMatch(
            restriction -> {
              int r1 = findRoot(parents, restriction[0]);
              int r2 = findRoot(parents, restriction[1]);

              return !((r1 == root1 && r2 == root2) || (r1 == root2 && r2 == root1));
            });
  }

  int findRoot(int[] parents, int node) {
    int root = node;
    while (parents[root] != -1) {
      root = parents[root];
    }

    int p = node;
    while (p != root) {
      int next = parents[p];
      parents[p] = root;

      p = next;
    }

    return root;
  }
}
