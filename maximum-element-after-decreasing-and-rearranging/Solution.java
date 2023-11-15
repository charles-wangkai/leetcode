import java.util.Arrays;

class Solution {
  public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    int[] sorted = Arrays.stream(arr).boxed().sorted().mapToInt(Integer::intValue).toArray();

    int result = 0;
    for (int i = 0; i < sorted.length; ++i) {
      result = Math.min(result + 1, sorted[i]);
    }

    return result;
  }
}
