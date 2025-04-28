import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int[] baseUnitConversions(int[][] conversions) {
    int n = conversions.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < conversions.length; ++i) {
      edgeLists[conversions[i][0]].add(i);
    }

    int[] baseUnits = new int[n];
    search(baseUnits, conversions, edgeLists, 0, 1);

    return baseUnits;
  }

  void search(
      int[] baseUnits, int[][] conversions, List<Integer>[] edgeLists, int node, int baseUnit) {
    baseUnits[node] = baseUnit;

    for (int edge : edgeLists[node]) {
      search(
          baseUnits,
          conversions,
          edgeLists,
          conversions[edge][1],
          multiplyMod(baseUnit, conversions[edge][2]));
    }
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}