import java.util.Arrays;

class Solution {
  public int minCostToMoveChips(int[] chips) {
    return (int)
        Math.min(
            Arrays.stream(chips).filter(chip -> chip % 2 == 0).count(),
            Arrays.stream(chips).filter(chip -> chip % 2 != 0).count());
  }
}
