import java.util.stream.IntStream;

class Solution {
  public int[] circularGameLosers(int n, int k) {
    boolean[] visited = new boolean[n];
    visited[0] = true;
    int index = 0;
    for (int step = k; ; step += k) {
      index = (index + step) % n;
      if (visited[index]) {
        break;
      }

      visited[index] = true;
    }

    return IntStream.range(0, visited.length).filter(i -> !visited[i]).map(i -> i + 1).toArray();
  }
}
