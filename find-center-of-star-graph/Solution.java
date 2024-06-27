import java.util.stream.IntStream;

class Solution {
  public int findCenter(int[][] edges) {
    int n = edges.length + 1;

    int[] degrees = new int[n];
    for (int[] edge : edges) {
      ++degrees[edge[0] - 1];
      ++degrees[edge[1] - 1];
    }

    return IntStream.range(0, degrees.length).filter(i -> degrees[i] != 1).findAny().getAsInt() + 1;
  }
}
