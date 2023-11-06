import java.util.stream.IntStream;

class Solution {
  public int findChampion(int n, int[][] edges) {
    boolean[] weaks = new boolean[n];
    for (int[] edge : edges) {
      weaks[edge[1]] = true;
    }

    int[] candidates = IntStream.range(0, weaks.length).filter(i -> !weaks[i]).toArray();

    return (candidates.length == 1) ? candidates[0] : -1;
  }
}
