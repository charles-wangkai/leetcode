import java.util.stream.IntStream;

class Solution {
  public int findJudge(int n, int[][] trust) {
    int[] trusting = new int[n + 1];
    int[] trusted = new int[n + 1];
    for (int[] t : trust) {
      ++trusting[t[0]];
      ++trusted[t[1]];
    }

    int[] candidates =
        IntStream.rangeClosed(1, n).filter(i -> trusting[i] == 0 && trusted[i] == n - 1).toArray();

    return (candidates.length == 1) ? candidates[0] : -1;
  }
}
