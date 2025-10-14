import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int[] queryConversions(int[][] conversions, int[][] queries) {
    int n = conversions.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < conversions.length; ++i) {
      edgeLists[conversions[i][0]].add(i);
      edgeLists[conversions[i][1]].add(i);
    }

    int[] unified = new int[n];
    search(unified, conversions, edgeLists, -1, 0, 1);

    return Arrays.stream(queries)
        .mapToInt(query -> divideMod(unified[query[1]], unified[query[0]]))
        .toArray();
  }

  void search(
      int[] unified, int[][] conversions, List<Integer>[] edgeLists, int parent, int node, int u) {
    unified[node] = u;

    for (int edge : edgeLists[node]) {
      int other = (conversions[edge][0] == node) ? conversions[edge][1] : conversions[edge][0];
      if (other != parent) {
        search(
            unified,
            conversions,
            edgeLists,
            node,
            other,
            (conversions[edge][0] == node)
                ? multiplyMod(u, conversions[edge][2])
                : divideMod(u, conversions[edge][2]));
      }
    }
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }
}