import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfPermutations(int n, int[][] requirements) {
    Map<Integer, Integer> endIndexToInversionNum =
        Arrays.stream(requirements)
            .collect(
                Collectors.toMap(requirement -> requirement[0], requirement -> requirement[1]));
    int maxInversionNum =
        Arrays.stream(requirements).mapToInt(requirement -> requirement[1]).max().getAsInt();

    Map<Integer, Integer> dp =
        (endIndexToInversionNum.getOrDefault(0, 0) == 0) ? Map.of(0, 1) : Map.of();
    for (int i = 1; i < n; ++i) {
      Map<Integer, Integer> nextDp = new HashMap<>();
      for (int inversionNum : dp.keySet()) {
        for (int j = 0; j <= i; ++j) {
          int nextInversionNum = inversionNum + j;
          if (nextInversionNum <= maxInversionNum
              && endIndexToInversionNum.getOrDefault(i, nextInversionNum) == nextInversionNum) {
            nextDp.put(
                nextInversionNum,
                addMod(nextDp.getOrDefault(nextInversionNum, 0), dp.get(inversionNum)));
          }
        }
      }

      dp = nextDp;
    }

    return dp.values().stream().reduce(this::addMod).orElse(0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}