import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countOfPairs(int[] nums) {
    Map<Integer, Integer> dp = new HashMap<>();
    for (int last1 = 0; last1 <= nums[0]; ++last1) {
      dp.put(last1, 1);
    }

    for (int i = 1; i < nums.length; ++i) {
      Map<Integer, Integer> nextDp = new HashMap<>();
      for (int nextLast1 = 0; nextLast1 <= nums[i]; ++nextLast1) {
        int nextLast2 = nums[i] - nextLast1;

        int pairNum = 0;
        for (int last1 : dp.keySet()) {
          int last2 = nums[i - 1] - last1;
          if (nextLast1 >= last1 && nextLast2 <= last2) {
            pairNum = addMod(pairNum, dp.get(last1));
          }
        }

        nextDp.put(nextLast1, pairNum);
      }

      dp = nextDp;
    }

    return dp.values().stream().mapToInt(Integer::intValue).reduce(this::addMod).getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}