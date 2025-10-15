import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int maxIncreasingSubarrays(List<Integer> nums) {
    int[] increasingLengths = new int[nums.size()];
    for (int i = increasingLengths.length - 1; i >= 0; --i) {
      increasingLengths[i] =
          ((i == increasingLengths.length - 1 || nums.get(i) >= nums.get(i + 1))
                  ? 0
                  : increasingLengths[i + 1])
              + 1;
    }

    int result = -1;
    int lower = 1;
    int upper = nums.size() / 2;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(increasingLengths, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] increasingLengths, int k) {
    return IntStream.rangeClosed(0, increasingLengths.length - 2 * k)
        .anyMatch(i -> increasingLengths[i] >= k && increasingLengths[i + k] >= k);
  }
}