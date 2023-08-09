// https://leetcode.com/problems/maximum-elegance-of-a-k-length-subsequence/solutions/3869920/java-c-python-sort-by-profit/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public long findMaximumElegance(int[][] items, int k) {
    Arrays.sort(items, Comparator.<int[], Integer>comparing(item -> item[0]).reversed());

    long result = 0;
    long profitSum = 0;
    Deque<Integer> duplicates = new ArrayDeque<>();
    Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < items.length; ++i) {
      if (i < k) {
        if (seen.contains(items[i][1])) {
          duplicates.push(items[i][0]);
        }

        profitSum += items[i][0];
      } else if (!seen.contains(items[i][1])) {
        if (duplicates.isEmpty()) {
          break;
        }

        profitSum += items[i][0] - duplicates.pop();
      }

      seen.add(items[i][1]);

      result = Math.max(result, profitSum + (long) seen.size() * seen.size());
    }

    return result;
  }
}
