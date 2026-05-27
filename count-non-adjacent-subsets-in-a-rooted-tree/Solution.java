import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int countValidSubsets(int[] parent, int[] nums, int k) {
    int n = parent.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < parent.length; ++i) {
      childLists[parent[i]].add(i);
    }

    int[][][] dp = new int[n][2][k];
    search(dp, nums, k, childLists, 0);

    return MOD_INT.addMod(MOD_INT.addMod(dp[0][0][0], dp[0][1][0]), -1);
  }

  void search(int[][][] dp, int[] nums, int k, List<Integer>[] childLists, int node) {
    for (int child : childLists[node]) {
      search(dp, nums, k, childLists, child);
    }

    dp[node][0] =
        merge(
            k,
            0,
            childLists[node].stream()
                .map(
                    i ->
                        IntStream.range(0, k)
                            .map(j -> MOD_INT.addMod(dp[i][0][j], dp[i][1][j]))
                            .toArray())
                .toList());

    dp[node][1] = merge(k, nums[node] % k, childLists[node].stream().map(i -> dp[i][0]).toList());
  }

  int[] merge(int k, int originalSum, List<int[]> wayNums) {
    int[] result = new int[k];
    result[originalSum] = 1;

    for (int[] w : wayNums) {
      result = combine(result, w);
    }

    return result;
  }

  int[] combine(int[] a, int[] b) {
    int k = a.length;

    int[] result = new int[k];
    for (int i = 0; i < a.length; ++i) {
      for (int j = 0; j < b.length; ++j) {
        result[(i + j) % k] = MOD_INT.addMod(result[(i + j) % k], MOD_INT.multiplyMod(a[i], b[j]));
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
