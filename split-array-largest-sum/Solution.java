import java.util.Arrays;

class Solution {
  public int splitArray(int[] nums, int m) {
    int result = -1;
    int lower = Arrays.stream(nums).max().getAsInt();
    int upper = Arrays.stream(nums).sum();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, m, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int m, int limit) {
    int subarrayCount = 1;
    int sum = 0;
    for (int num : nums) {
      if (sum + num <= limit) {
        sum += num;
      } else {
        ++subarrayCount;
        sum = num;
      }
    }

    return subarrayCount <= m;
  }
}
