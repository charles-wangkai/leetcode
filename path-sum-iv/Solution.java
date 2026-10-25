import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public int pathSum(int[] nums) {
    Map<Integer, Integer> posToValue =
        Arrays.stream(nums).boxed().collect(Collectors.toMap(num -> num / 10, num -> num % 10));

    return Arrays.stream(nums)
        .map(num -> num / 10)
        .filter(pos -> isLeaf(posToValue, pos))
        .map(pos -> computePathSum(posToValue, pos))
        .sum();
  }

  boolean isLeaf(Map<Integer, Integer> posToValue, int pos) {
    int depth = pos / 10;
    int horizontal = pos % 10;

    return !posToValue.containsKey((depth + 1) * 10 + horizontal * 2 - 1)
        && !posToValue.containsKey((depth + 1) * 10 + horizontal * 2);
  }

  int computePathSum(Map<Integer, Integer> posToValue, int pos) {
    int result = 0;
    while (true) {
      result += posToValue.get(pos);

      int depth = pos / 10;
      if (depth == 1) {
        break;
      }

      int horizontal = pos % 10;
      pos = (depth - 1) * 10 + (horizontal + 1) / 2;
    }

    return result;
  }
}
