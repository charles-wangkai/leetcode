import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minSwaps(int[] nums) {
    List<Integer> evenIndices = new ArrayList<>();
    List<Integer> oddIndices = new ArrayList<>();
    for (int i = 0; i < nums.length; ++i) {
      ((nums[i] % 2 == 0) ? evenIndices : oddIndices).add(i);
    }

    if (Math.abs(evenIndices.size() - oddIndices.size()) > 1) {
      return -1;
    }

    int result = Integer.MAX_VALUE;
    if (evenIndices.size() >= oddIndices.size()) {
      result =
          Math.min(
              result,
              IntStream.range(0, evenIndices.size())
                  .map(i -> Math.abs(evenIndices.get(i) - i * 2))
                  .sum());
    }
    if (oddIndices.size() >= evenIndices.size()) {
      result =
          Math.min(
              result,
              IntStream.range(0, oddIndices.size())
                  .map(i -> Math.abs(oddIndices.get(i) - i * 2))
                  .sum());
    }

    return result;
  }
}