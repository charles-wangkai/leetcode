import java.util.ArrayList;
import java.util.List;

class Solution {
  public int maxSubarrayLength(int[] nums) {
    int result = 0;
    List<Integer> increasing = new ArrayList<>();
    increasing.add(0);
    for (int i = 1; i < nums.length; ++i) {
      if (nums[i] > nums[increasing.get(increasing.size() - 1)]) {
        increasing.add(i);
      } else if (nums[i] < nums[increasing.get(increasing.size() - 1)]) {
        int beginIndex = -1;
        int lower = 0;
        int upper = increasing.size() - 1;
        while (lower <= upper) {
          int middle = (lower + upper) / 2;
          if (nums[increasing.get(middle)] > nums[i]) {
            beginIndex = middle;
            upper = middle - 1;
          } else {
            lower = middle + 1;
          }
        }

        result = Math.max(result, i - increasing.get(beginIndex) + 1);
      }
    }

    return result;
  }
}
