import java.util.stream.IntStream;

class Solution {
  public int findCenter(int[][] edges) {
    int[] degrees = new int[edges.length + 2];
    for (int[] edge : edges) {
      ++degrees[edge[0]];
      ++degrees[edge[1]];
    }

    return IntStream.range(1, degrees.length).filter(i -> degrees[i] != 1).findAny().getAsInt();
  }
}
