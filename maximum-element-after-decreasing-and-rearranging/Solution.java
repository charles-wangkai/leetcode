import java.util.Arrays;

class Solution {
  public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    Arrays.sort(arr);

    int result = 0;
    for (int value : arr) {
      result = Math.min(result + 1, value);
    }

    return result;
  }
}
