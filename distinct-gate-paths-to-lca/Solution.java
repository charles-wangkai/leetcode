import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int distinctPaths(int n, int[] parent, int[][] gates, int[][] queries) {
    Tree tree =
        new Tree(
            gates,
            IntStream.range(1, n).toArray(),
            IntStream.range(1, n).map(i -> parent[i]).toArray(),
            0);

    return Arrays.stream(queries)
        .mapToInt(query -> {
          int aNode = query[0];
          int aCard = query[1];
          int bNode = query[2];
          int bCard = query[3];

          int lca = tree.findLca(aNode, bNode);

          int[] aState =
              MOD_INT.multiplyVM(
                  buildState(aCard),
                  tree.computeTransition(aNode, tree.depths[aNode] - tree.depths[lca]));
          int[] bState =
              MOD_INT.multiplyVM(
                  buildState(bCard),
                  tree.computeTransition(bNode, tree.depths[bNode] - tree.depths[lca]));

          return MOD_INT.multiplyMod(
              MOD_INT.addMod(aState[0], aState[1]), MOD_INT.addMod(bState[0], bState[1]));
        })
        .reduce(0, (acc, x) -> acc ^ x);
  }

  int[] buildState(int card) {
    return (card == 1) ? new int[] {1, 0} : new int[] {0, 1};
  }
}

class Tree {
  int n;
  int[][] gates;
  int[] u;
  int[] v;
  int root;
  List<Integer>[] edgeLists;
  int[] depths;
  int[][] ancestors;
  int[][][][] ancestorTransitions;

  @SuppressWarnings("unchecked")
  Tree(int[][] gates, int[] u, int[] v, int root) {
    n = u.length + 1;

    this.gates = gates;
    this.u = u;
    this.v = v;
    this.root = root;

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
    ancestorTransitions = new int[n][Integer.toBinaryString(n).length()][2][2];
    init(0, -1, root);
  }

  private void init(int depth, int parent, int node) {
    depths[node] = depth;

    ancestors[node][0] = parent;

    setOneStepTransition(node);

    for (int i = 1; i < ancestors[node].length; ++i) {
      if (ancestors[node][i - 1] == -1) {
        ancestors[node][i] = -1;
      } else {
        ancestors[node][i] = ancestors[ancestors[node][i - 1]][i - 1];

        ancestorTransitions[node][i] =
            Solution.MOD_INT.multiplyMM(
                ancestorTransitions[node][i - 1],
                ancestorTransitions[ancestors[node][i - 1]][i - 1]);
      }
    }

    for (int edge : edgeLists[node]) {
      int adj = (node == u[edge]) ? v[edge] : u[edge];
      if (adj != parent) {
        init(depth + 1, node, adj);
      }
    }
  }

  private void setOneStepTransition(int node) {
    int red = gates[node][0];
    int blue = gates[node][1];
    int white = gates[node][2];

    ancestorTransitions[node][0][0][0] = red;
    ancestorTransitions[node][0][0][1] = white;
    ancestorTransitions[node][0][1][0] = white;
    ancestorTransitions[node][0][1][1] = blue;
  }

  int[][] computeTransition(int node, int step) {
    int[][] result = {{1, 0}, {0, 1}};

    for (int i = ancestors[node].length - 1; i >= 0; --i) {
      if ((1 << i) <= step) {
        result = Solution.MOD_INT.multiplyMM(result, ancestorTransitions[node][i]);

        node = ancestors[node][i];
        step -= 1 << i;
      }
    }

    return result;
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

  int[][] multiplyMM(int[][] m1, int[][] m2) {
    int size = m1.length;

    int[][] result = new int[size][size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        for (int k = 0; k < size; ++k) {
          result[i][j] = addMod(result[i][j], multiplyMod(m1[i][k], m2[k][j]));
        }
      }
    }

    return result;
  }

  int[] multiplyVM(int[] v, int[][] m) {
    int size = v.length;

    int[] result = new int[size];
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        result[i] = addMod(result[i], multiplyMod(v[j], m[j][i]));
      }
    }

    return result;
  }
}
