import java.util.HashMap;
import java.util.Map;

public class Solution {
  public int pathSum(int[] nums) {
    Map<Integer, Integer> pos2value = new HashMap<>();
    for (int num : nums) {
      int pos = num / 10;
      int value = num % 10;

      pos2value.put(pos, value);
    }

    int result = 0;
    for (int num : nums) {
      int pos = num / 10;

      if (isLeaf(pos2value, pos)) {
        result += computePathSum(pos2value, pos);
      }
    }
    return result;
  }

  boolean isLeaf(Map<Integer, Integer> pos2value, int pos) {
    int depth = pos / 10;
    int horizontal = pos % 10;

    return !pos2value.containsKey((depth + 1) * 10 + horizontal * 2 - 1)
        && !pos2value.containsKey((depth + 1) * 10 + horizontal * 2);
  }

  int computePathSum(Map<Integer, Integer> pos2value, int pos) {
    int pathSum = 0;
    while (true) {
      pathSum += pos2value.get(pos);

      int depth = pos / 10;
      if (depth == 1) {
        break;
      }

      int horizontal = pos % 10;
      pos = (depth - 1) * 10 + (horizontal + 1) / 2;
    }
    return pathSum;
  }
}
