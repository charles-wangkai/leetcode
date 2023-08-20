import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minimumOperations(List<Integer> nums) {
    int result = Integer.MAX_VALUE;
    for (int length1 = 0; length1 <= nums.size(); ++length1) {
      for (int length2 = 0; length1 + length2 <= nums.size(); ++length2) {
        result =
            Math.min(
                result,
                (int) IntStream.range(0, length1).filter(i -> nums.get(i) != 1).count()
                    + (int)
                        IntStream.range(length1, length1 + length2)
                            .filter(i -> nums.get(i) != 2)
                            .count()
                    + (int)
                        IntStream.range(length1 + length2, nums.size())
                            .filter(i -> nums.get(i) != 3)
                            .count());
      }
    }

    return result;
  }
}
