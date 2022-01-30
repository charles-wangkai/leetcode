import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> maxScoreIndices(int[] nums) {
    int n = nums.length;

    int[] leftZeroCounts = new int[n + 1];
    for (int i = 1; i < leftZeroCounts.length; ++i) {
      leftZeroCounts[i] = leftZeroCounts[i - 1] + ((nums[i - 1] == 0) ? 1 : 0);
    }

    int[] rightOneCounts = new int[n + 1];
    for (int i = n - 1; i >= 0; --i) {
      rightOneCounts[i] = rightOneCounts[i + 1] + ((nums[i] == 1) ? 1 : 0);
    }

    int maxScore =
        IntStream.rangeClosed(0, n)
            .map(i -> leftZeroCounts[i] + rightOneCounts[i])
            .max()
            .getAsInt();

    return IntStream.rangeClosed(0, n)
        .filter(i -> leftZeroCounts[i] + rightOneCounts[i] == maxScore)
        .boxed()
        .collect(Collectors.toList());
  }
}