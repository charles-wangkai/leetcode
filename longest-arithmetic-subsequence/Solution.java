import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestArithSeqLength(int[] nums) {
    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] deltaToLengthArray = new Map[nums.length];
    for (int i = 0; i < deltaToLengthArray.length; ++i) {
      deltaToLengthArray[i] = new HashMap<>();
    }

    for (int i = 0; i < nums.length; ++i) {
      for (int j = 0; j < i; ++j) {
        int delta = nums[i] - nums[j];

        deltaToLengthArray[i].put(
            delta,
            Math.max(
                deltaToLengthArray[i].getOrDefault(delta, Integer.MIN_VALUE),
                deltaToLengthArray[j].getOrDefault(delta, 1) + 1));
      }
    }

    return Arrays.stream(deltaToLengthArray)
        .skip(1)
        .mapToInt(
            deltaToLength ->
                deltaToLength.values().stream().mapToInt(Integer::intValue).max().getAsInt())
        .max()
        .getAsInt();
  }
}
