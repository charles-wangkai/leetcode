import java.util.HashMap;
import java.util.Map;

class Solution {
  public int tallestBillboard(int[] rods) {
    Map<Integer, Integer> diffToMaxHeight = Map.of(0, 0);

    for (int rod : rods) {
      Map<Integer, Integer> nextDiffToMaxHeight = new HashMap<>(diffToMaxHeight);
      for (int diff : diffToMaxHeight.keySet()) {
        nextDiffToMaxHeight.put(
            diff + rod,
            Math.max(nextDiffToMaxHeight.getOrDefault(diff + rod, 0), diffToMaxHeight.get(diff)));

        nextDiffToMaxHeight.put(
            Math.abs(diff - rod),
            Math.max(
                nextDiffToMaxHeight.getOrDefault(Math.abs(diff - rod), 0),
                diffToMaxHeight.get(diff) + Math.min(diff, rod)));
      }

      diffToMaxHeight = nextDiffToMaxHeight;
    }

    return diffToMaxHeight.get(0);
  }
}
