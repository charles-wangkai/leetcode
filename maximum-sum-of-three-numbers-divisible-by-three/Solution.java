import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int maximumSum(int[] nums) {
    int[] candidates =
        IntStream.range(0, 3)
            .flatMap(
                r ->
                    Arrays.stream(nums)
                        .filter(x -> x % 3 == r)
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .mapToInt(Integer::intValue)
                        .limit(3))
            .toArray();

    int result = 0;
    for (int i = 0; i < candidates.length; ++i) {
      for (int j = i + 1; j < candidates.length; ++j) {
        for (int k = j + 1; k < candidates.length; ++k) {
          if ((candidates[i] + candidates[j] + candidates[k]) % 3 == 0) {
            result = Math.max(result, candidates[i] + candidates[j] + candidates[k]);
          }
        }
      }
    }

    return result;
  }
}