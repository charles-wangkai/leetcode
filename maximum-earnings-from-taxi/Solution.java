import java.util.ArrayList;
import java.util.List;

class Solution {
  public long maxTaxiEarnings(int n, int[][] rides) {
    @SuppressWarnings("unchecked")
    List<Integer>[] rideLists = new List[n + 1];
    for (int i = 0; i < rideLists.length; ++i) {
      rideLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < rides.length; ++i) {
      rideLists[rides[i][1]].add(i);
    }

    long[] earnings = new long[n + 1];
    for (int i = 1; i < earnings.length; ++i) {
      earnings[i] = earnings[i - 1];
      for (int r : rideLists[i]) {
        int[] ride = rides[r];
        earnings[i] = Math.max(earnings[i], earnings[ride[0]] + ride[1] - ride[0] + ride[2]);
      }
    }

    return earnings[n];
  }
}
