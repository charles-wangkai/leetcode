// https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length/solutions/3803590/simple-python-math-solution-o-nlogn-time-complexity-only-7-lines-of-code/

import java.util.Collections;
import java.util.List;

class Solution {
  public int maxIncreasingGroups(List<Integer> usageLimits) {
    Collections.sort(usageLimits);

    int result = 0;
    long sum = 0;
    for (int usageLimit : usageLimits) {
      sum += usageLimit;
      if (sum >= (result + 1L) * (result + 2) / 2) {
        ++result;
      }
    }

    return result;
  }
}
