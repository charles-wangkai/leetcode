import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] sortEvenOdd(int[] nums) {
    int[] evenSorted =
        IntStream.range(0, nums.length)
            .filter(i -> i % 2 == 0)
            .map(i -> nums[i])
            .sorted()
            .toArray();
    int[] oddSorted =
        IntStream.range(0, nums.length)
            .filter(i -> i % 2 != 0)
            .map(i -> nums[i])
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(x -> x)
            .toArray();

    return IntStream.range(0, nums.length)
        .map(i -> (i % 2 == 0) ? evenSorted[i / 2] : oddSorted[i / 2])
        .toArray();
  }
}