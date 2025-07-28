// https://leetcode.com/problems/partition-array-for-maximum-xor-and-and/solutions/7009864/maximize-custom-xor-and-and-expression-using-subsets-and-linear-basis/
// https://codeforces.com/blog/entry/68953

import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int BIT_NUM = 30;

  public long maximizeXorAndXor(int[] nums) {
    long result = 0;
    for (int mask = 0; mask < 1 << nums.length; ++mask) {
      int selectedAnd = -1;
      int unselectedXor = 0;
      List<Integer> unselected = new ArrayList<>();
      for (int i = 0; i < nums.length; ++i) {
        if (((mask >> i) & 1) == 1) {
          if (selectedAnd == -1) {
            selectedAnd = nums[i];
          } else {
            selectedAnd &= nums[i];
          }
        } else {
          unselectedXor ^= nums[i];
          unselected.add(nums[i]);
        }
      }

      if (selectedAnd == -1) {
        selectedAnd = 0;
      }

      for (int i = 0; i < unselected.size(); ++i) {
        unselected.set(i, unselected.get(i) & ~unselectedXor);
      }

      result = Math.max(result, selectedAnd + unselectedXor + 2L * computeMaxXor(unselected));
    }

    return result;
  }

  int computeMaxXor(List<Integer> a) {
    int[] basis = new int[BIT_NUM];
    for (int ai : a) {
      int mask = ai;
      for (int b = BIT_NUM - 1; b >= 0; --b) {
        if (((mask >> b) & 1) == 1) {
          if (basis[b] == 0) {
            basis[b] = mask;

            break;
          }

          mask ^= basis[b];
        }
      }
    }

    int result = 0;
    for (int b = BIT_NUM - 1; b >= 0; --b) {
      if (basis[b] != 0 && ((result >> b) & 1) == 0) {
        result ^= basis[b];
      }
    }

    return result;
  }
}
