import java.util.HashMap;
import java.util.Map;

class Solution {
  public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
    int result = -1;
    int lower = 1;
    int upper = changeIndices.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, changeIndices, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int[] changeIndices, int time) {
    Map<Integer, Integer> posToLastIndex = new HashMap<>();
    for (int i = time - 1; i >= 0; --i) {
      if (!posToLastIndex.containsKey(changeIndices[i])) {
        posToLastIndex.put(changeIndices[i], i);
      }
    }
    if (posToLastIndex.size() != nums.length) {
      return false;
    }

    boolean[] forMarks = new boolean[time];
    for (int lastIndex : posToLastIndex.values()) {
      forMarks[lastIndex] = true;
    }

    int rest = 0;
    for (int i = 0; i < forMarks.length; ++i) {
      if (forMarks[i]) {
        if (rest < nums[changeIndices[i] - 1]) {
          return false;
        }

        rest -= nums[changeIndices[i] - 1];
      } else {
        ++rest;
      }
    }

    return true;
  }
}