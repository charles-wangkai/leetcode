import java.util.Arrays;

class Solution {
  public int minCostToMoveChips(int[] position) {
    return (int)
        Math.min(
            Arrays.stream(position).filter(chip -> chip % 2 == 0).count(),
            Arrays.stream(position).filter(chip -> chip % 2 != 0).count());
  }
}
