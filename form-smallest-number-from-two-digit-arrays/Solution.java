import java.util.Arrays;

class Solution {
  public int minNumber(int[] nums1, int[] nums2) {
    for (int i = 1; ; ++i) {
      if (contains(nums1, i) && contains(nums2, i)) {
        return i;
      }
    }
  }

  boolean contains(int[] digits, int value) {
    String s = String.valueOf(value);

    return Arrays.stream(digits).anyMatch(digit -> s.indexOf(digit + '0') != -1);
  }
}
