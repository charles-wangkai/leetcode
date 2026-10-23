import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long maxSum(int[] nums, int length, int k) {
    return IntStream.rangeClosed(0, nums.length - length)
        .mapToLong(beginIndex -> computeMaxSum(nums, length, k, beginIndex))
        .max()
        .getAsLong();
  }

  long computeMaxSum(int[] nums, int length, int k, int beginIndex) {
    int endIndex = beginIndex + length - 1;

    int[] sortedChosen =
        IntStream.rangeClosed(beginIndex, endIndex).map(i -> nums[i]).sorted().toArray();

    int[] sortedCandidates =
        IntStream.concat(IntStream.range(0, beginIndex), IntStream.range(endIndex + 1, nums.length))
            .map(i -> nums[i])
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    long result = Arrays.stream(sortedChosen).asLongStream().sum();
    for (int i = 0;
        i < k
            && i < sortedChosen.length
            && i < sortedCandidates.length
            && sortedCandidates[i] > sortedChosen[i];
        ++i) {
      result += sortedCandidates[i] - sortedChosen[i];
    }

    return result;
  }
}