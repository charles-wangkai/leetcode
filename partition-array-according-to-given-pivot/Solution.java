import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] pivotArray(int[] nums, int pivot) {
    return IntStream.concat(
            IntStream.concat(
                Arrays.stream(nums).filter(x -> x < pivot),
                Arrays.stream(nums).filter(x -> x == pivot)),
            Arrays.stream(nums).filter(x -> x > pivot))
        .toArray();
  }
}