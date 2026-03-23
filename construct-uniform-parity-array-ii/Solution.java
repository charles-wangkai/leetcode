import java.util.Arrays;

class Solution {
  public boolean uniformArray(int[] nums1) {
    return Arrays.stream(nums1).map(x -> x % 2).distinct().count() == 1
        || Arrays.stream(nums1).min().getAsInt() % 2 == 1;
  }
}