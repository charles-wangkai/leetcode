class Solution {
  public int maximumDetonation(int[][] bombs) {
    int result = 0;
    for (int i = 0; i < bombs.length; ++i) {
      result = Math.max(result, search(bombs, new boolean[bombs.length], i));
    }

    return result;
  }

  int search(int[][] bombs, boolean[] visited, int v) {
    visited[v] = true;

    int result = 1;
    for (int i = 0; i < bombs.length; ++i) {
      if (!visited[i] && canReach(bombs, v, i)) {
        result += search(bombs, visited, i);
      }
    }

    return result;
  }

  boolean canReach(int[][] bombs, int from, int to) {
    return (long) (bombs[from][0] - bombs[to][0]) * (bombs[from][0] - bombs[to][0])
            + (long) (bombs[from][1] - bombs[to][1]) * (bombs[from][1] - bombs[to][1])
        <= (long) bombs[from][2] * bombs[from][2];
  }
}