import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
  public int maxTotalReward(int[] rewardValues) {
    Arrays.sort(rewardValues);

    SortedSet<Integer> totals = new TreeSet<>();
    totals.add(0);

    for (int rewardValue : rewardValues) {
      SortedSet<Integer> nextTotals = new TreeSet<>(totals);
      for (int total : totals) {
        if (rewardValue <= total) {
          break;
        }

        nextTotals.add(total + rewardValue);
      }

      totals = nextTotals;
    }

    return totals.last();
  }
}