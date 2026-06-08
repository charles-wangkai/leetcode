import java.util.Arrays;
import java.util.stream.Stream;

class Solution {
  public int[] pivotArray(int[] nums, int pivot) {
    return Stream.of(
            Arrays.stream(nums).filter(x -> x < pivot),
            Arrays.stream(nums).filter(x -> x == pivot),
            Arrays.stream(nums).filter(x -> x > pivot))
        .flatMapToInt(stream -> stream)
        .toArray();
  }
}