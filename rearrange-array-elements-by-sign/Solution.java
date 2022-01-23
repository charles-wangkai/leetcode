import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] rearrangeArray(int[] nums) {
    int[] positives = Arrays.stream(nums).filter(x -> x > 0).toArray();
    int[] negatives = Arrays.stream(nums).filter(x -> x < 0).toArray();

    return IntStream.range(0, nums.length)
        .map(i -> (i % 2 == 0) ? positives[i / 2] : negatives[i / 2])
        .toArray();
  }
}