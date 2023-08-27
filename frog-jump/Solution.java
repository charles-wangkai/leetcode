import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  public boolean canCross(int[] stones) {
    Map<Integer, Set<Integer>> stoneToLastJumps = new HashMap<>();
    for (int stone : stones) {
      stoneToLastJumps.put(stone, new HashSet<>());
    }
    stoneToLastJumps.get(0).add(0);

    for (int i = 0; i < stones.length; ++i) {
      for (int lastJump : stoneToLastJumps.get(stones[i])) {
        for (int delta = -1; delta <= 1; ++delta) {
          int jump = lastJump + delta;
          if (jump > 0) {
            int nextStone = stones[i] + jump;
            if (stoneToLastJumps.containsKey(nextStone)) {
              stoneToLastJumps.get(nextStone).add(jump);
            }
          }
        }
      }
    }

    return !stoneToLastJumps.get(stones[stones.length - 1]).isEmpty();
  }
}
