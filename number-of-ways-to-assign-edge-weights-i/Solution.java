import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int assignEdgeWeights(int[][] edges) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0] - 1].add(edge[1] - 1);
      adjLists[edge[1] - 1].add(edge[0] - 1);
    }

    int maxDepth = search(adjLists, -1, 0);

    int result = 0;
    int c = 1;
    for (int i = 1; i <= maxDepth; ++i) {
      c = multiplyMod(c, divideMod(maxDepth - i + 1, i));
      if (i % 2 == 1) {
        result = addMod(result, c);
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int search(List<Integer>[] adjLists, int parent, int node) {
    int result = 0;
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        result = Math.max(result, 1 + search(adjLists, node, adj));
      }
    }

    return result;
  }
}