import java.util.Arrays;

class Solution {
  public int minimizeMax(int[] nums, int p) {
    Arrays.sort(nums);

    int result = -1;
    int lower = 0;
    int upper = Arrays.stream(nums).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, p, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int p, int diff) {
    int pairCount = 0;
    int index = 0;
    while (index + 1 < nums.length) {
      if (nums[index + 1] - nums[index] <= diff) {
        ++pairCount;
        index += 2;
      } else {
        ++index;
      }
    }

    return pairCount >= p;
  }
}
