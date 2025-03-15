import java.util.Arrays;

class Solution {
  public int minCapability(int[] nums, int k) {
    int result = -1;
    int lower = 0;
    int upper = Arrays.stream(nums).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, k, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k, int capability) {
    int houseNum = 0;
    int length = 0;
    for (int i = 0; i <= nums.length; ++i) {
      if (i != nums.length && nums[i] <= capability) {
        ++length;
      } else {
        houseNum += (length + 1) / 2;
        length = 0;
      }
    }

    return houseNum >= k;
  }
}
