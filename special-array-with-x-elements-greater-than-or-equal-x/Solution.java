import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int specialArray(int[] nums) {
    return IntStream.rangeClosed(0, 100)
        .filter(x -> Arrays.stream(nums).filter(num -> num >= x).count() == x)
        .findAny()
        .orElse(-1);
  }
}
