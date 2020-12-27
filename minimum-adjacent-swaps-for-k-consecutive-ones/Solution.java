import java.util.stream.IntStream;

class Solution {
  public int minMoves(int[] nums, int k) {
    int[] oneIndices = IntStream.range(0, nums.length).filter(i -> nums[i] == 1).toArray();

    long base = 0;
    for (int i = 0, j = k - 1; i < j; ++i, --j) {
      base += j - i;
    }

    int leftBeginIndex = 0;
    int leftEndIndex = k / 2 - 1;
    long leftSum =
        IntStream.rangeClosed(leftBeginIndex, leftEndIndex)
            .map(i -> oneIndices[i])
            .asLongStream()
            .sum();
    int rightBeginIndex = k - k / 2;
    int rightEndIndex = k - 1;
    long rightSum =
        IntStream.rangeClosed(rightBeginIndex, rightEndIndex)
            .map(i -> oneIndices[i])
            .asLongStream()
            .sum();

    long result = Long.MAX_VALUE;
    while (rightEndIndex != oneIndices.length) {
      result = Math.min(result, rightSum - leftSum - base);

      leftSum += oneIndices[leftEndIndex + 1] - oneIndices[leftBeginIndex];
      ++leftBeginIndex;
      ++leftEndIndex;
      if (rightEndIndex + 1 < oneIndices.length && rightBeginIndex < oneIndices.length) {
        rightSum += oneIndices[rightEndIndex + 1] - oneIndices[rightBeginIndex];
      }
      ++rightBeginIndex;
      ++rightEndIndex;
    }

    return (int) result;
  }
}
