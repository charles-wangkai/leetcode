class Solution {
  public boolean increasingTriplet(int[] nums) {
    Integer min = null;
    Integer first = null;
    Integer second = null;
    for (int num : nums) {
      if (second != null && num > second) {
        return true;
      }

      if (first != null && num > first && (second == null || num < second)) {
        second = num;
      }

      if (min != null && num > min && (second == null || num < second)) {
        first = min;
        second = num;
      }

      if (min == null || num < min) {
        min = num;
      }
    }

    return false;
  }
}
