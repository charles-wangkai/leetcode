import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
    Tree tree =
        new Tree(
            Arrays.stream(edges).mapToInt(edge -> edge[0] - 1).toArray(),
            Arrays.stream(edges).mapToInt(edge -> edge[1] - 1).toArray());

    int[] powers = new int[tree.n];
    powers[0] = 1;
    for (int i = 1; i < powers.length; ++i) {
      powers[i] = MOD_INT.multiplyMod(powers[i - 1], 2);
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int distance =
                  tree.depths[query[0] - 1]
                      + tree.depths[query[1] - 1]
                      - 2 * tree.depths[tree.findLca(query[0] - 1, query[1] - 1)];

              return (distance == 0) ? 0 : powers[distance - 1];
            })
        .toArray();
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

class Tree {
  int n;
  int[] u;
  int[] v;
  List<Integer>[] edgeLists;
  int[] depths;
  int[][] ancestors;

  @SuppressWarnings("unchecked")
  Tree(int[] u, int[] v) {
    n = u.length + 1;

    this.u = u;
    this.v = v;

    edgeLists = new List[n];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < u.length; ++i) {
      edgeLists[u[i]].add(i);
      edgeLists[v[i]].add(i);
    }

    depths = new int[n];
    ancestors = new int[n][Integer.toBinaryString(n).length()];
    init(0, -1, 0);
  }

  private void init(int depth, int parent, int node) {
    depths[node] = depth;

    ancestors[node][0] = parent;
    for (int i = 1; i < ancestors[node].length; ++i) {
      ancestors[node][i] =
          (ancestors[node][i - 1] == -1) ? -1 : ancestors[ancestors[node][i - 1]][i - 1];
    }

    for (int edge : edgeLists[node]) {
      int adj = (node == u[edge]) ? v[edge] : u[edge];
      if (adj != parent) {
        init(depth + 1, node, adj);
      }
    }
  }

  int findLca(int node1, int node2) {
    if (depths[node1] < depths[node2]) {
      return findLca(node2, node1);
    }

    for (int i = ancestors[node1].length - 1; i >= 0; --i) {
      if (ancestors[node1][i] != -1 && depths[ancestors[node1][i]] >= depths[node2]) {
        node1 = ancestors[node1][i];
      }
    }

    if (node1 == node2) {
      return node1;
    }

    for (int i = ancestors[node1].length - 1; i >= 0; --i) {
      if (ancestors[node1][i] != ancestors[node2][i]) {
        node1 = ancestors[node1][i];
        node2 = ancestors[node2][i];
      }
    }

    return ancestors[node1][0];
  }
}
