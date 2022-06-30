import java.util.Arrays;

class Solution {
  public int minMoves2(int[] nums) {
    int[] sorted = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

    int result = 0;
    for (int i = 0, j = sorted.length - 1; i < j; ++i, --j) {
      result += sorted[j] - sorted[i];
    }

    return result;
  }
}
