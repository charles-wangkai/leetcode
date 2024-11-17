// https://leetcode.com/problems/minimize-the-maximum-adjacent-element-difference/solutions/6054022/binary-search/

class Solution {
  public int minDifference(int[] nums) {
    int maxGap = 0;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length - 1; ++i) {
      int smaller = Math.min(nums[i], nums[i + 1]);
      int larger = Math.max(nums[i], nums[i + 1]);
      if (smaller == -1 && larger != -1) {
        min = Math.min(min, larger);
        max = Math.max(max, larger);
      } else {
        maxGap = Math.max(maxGap, Math.abs(nums[i] - nums[i + 1]));
      }
    }

    if (min == Integer.MAX_VALUE) {
      return maxGap;
    }

    int result = maxGap;
    int lower = maxGap;
    int upper = (max - min + 1) / 2;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, min + middle, max - middle, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int x, int y, int d) {
    int count = 0;
    int prev = Integer.MIN_VALUE;
    for (int num : nums) {
      if (num == -1) {
        ++count;
      } else {
        if (count != 0 && prev != Integer.MIN_VALUE) {
          int gap =
              Math.min(
                  Math.max(Math.abs(prev - x), Math.abs(num - x)),
                  Math.max(Math.abs(prev - y), Math.abs(num - y)));
          if (gap > d && (count == 1 || y - x > d)) {
            return false;
          }
        }

        count = 0;
        prev = num;
      }
    }

    return true;
  }
}