import java.util.Arrays;

class Solution {
  public int partitionArray(int[] nums, int k) {
    int[] sorted = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

    int result = 0;
    int limit = -1;
    for (int x : sorted) {
      if (x > limit) {
        ++result;
        limit = x + k;
      }
    }

    return result;
  }
}