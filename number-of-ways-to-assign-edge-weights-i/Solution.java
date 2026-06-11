import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

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
      c = MOD_INT.multiplyMod(c, MOD_INT.divideMod(maxDepth - i + 1, i));
      if (i % 2 == 1) {
        result = MOD_INT.addMod(result, c);
      }
    }

    return result;
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

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return Math.floorMod(x, modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return mod(x + y);
  }

  int multiplyMod(int x, int y) {
    return mod((long) x * y);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, long exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
