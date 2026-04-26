import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int compareBitonicSums(int[] nums) {
    int middleIndex =
        IntStream.range(0, nums.length).boxed().max(Comparator.comparing(i -> nums[i])).get();
    long leftSum = IntStream.rangeClosed(0, middleIndex).map(i -> nums[i]).asLongStream().sum();
    long rightSum =
        IntStream.range(middleIndex, nums.length).map(i -> nums[i]).asLongStream().sum();
    if (leftSum > rightSum) {
      return 0;
    }
    if (leftSum < rightSum) {
      return 1;
    }

    return -1;
  }
}