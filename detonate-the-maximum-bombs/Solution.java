import java.util.stream.IntStream;

class Solution {
  public int maximumDetonation(int[][] bombs) {
    return IntStream.range(0, bombs.length)
        .map(i -> search(bombs, new boolean[bombs.length], i))
        .max()
        .getAsInt();
  }

  int search(int[][] bombs, boolean[] visited, int v) {
    visited[v] = true;

    return 1
        + IntStream.range(0, bombs.length)
            .filter(i -> !visited[i] && canReach(bombs, v, i))
            .map(i -> search(bombs, visited, i))
            .sum();
  }

  boolean canReach(int[][] bombs, int from, int to) {
    return (long) (bombs[from][0] - bombs[to][0]) * (bombs[from][0] - bombs[to][0])
            + (long) (bombs[from][1] - bombs[to][1]) * (bombs[from][1] - bombs[to][1])
        <= (long) bombs[from][2] * bombs[from][2];
  }
}
