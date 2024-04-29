// https://leetcode.com/problems/find-the-median-of-the-uniqueness-array/solutions/5081935/java-c-python-binary-search-sliding-window/

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int medianOfUniquenessArray(int[] nums) {
    int result = -1;
    int lower = 1;
    int upper = nums.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeLessEqualNum(nums, middle) >= (nums.length * (nums.length + 1L) / 2 + 1) / 2) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  long computeLessEqualNum(int[] nums, int maxUniqueSize) {
    long result = 0;
    int endIndex = -1;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1
          && (valueToCount.size() != maxUniqueSize
              || valueToCount.containsKey(nums[endIndex + 1]))) {
        ++endIndex;
        updateMap(valueToCount, nums[endIndex], 1);
      }

      result += endIndex - beginIndex + 1;

      updateMap(valueToCount, nums[beginIndex], -1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}