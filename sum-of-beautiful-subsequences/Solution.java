// https://leetcode.com/problems/sum-of-beautiful-subsequences/solutions/7140297/python-sieve/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int totalBeauty(int[] nums) {
    int maxValue = Arrays.stream(nums).max().getAsInt();

    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    int[] f = new int[maxValue + 1];
    for (int d = 1; d < f.length; ++d) {
      List<Integer> indices = new ArrayList<>();
      for (int i = d; i <= maxValue; i += d) {
        indices.addAll(valueToIndices.getOrDefault(i, List.of()));
      }
      Collections.sort(indices);

      if (indices.size() <= 1) {
        f[d] = indices.size();
      } else {
        Map<Integer, Integer> indexToRank =
            IntStream.range(0, indices.size())
                .boxed()
                .collect(Collectors.toMap(indices::get, i -> i + 1));

        FenwickTree fenwickTree = new FenwickTree(indices.size());
        for (int i = d; i <= maxValue; i += d) {
          for (int index : valueToIndices.getOrDefault(i, List.of()).reversed()) {
            int rank = indexToRank.get(index);
            int addend = 1 + fenwickTree.computePrefixSum(rank - 1);
            f[d] = MOD_INT.addMod(f[d], addend);
            fenwickTree.add(rank, addend);
          }
        }
      }
    }

    for (int d = f.length - 1; d >= 1; --d) {
      for (int i = 2 * d; i < f.length; i += d) {
        f[d] = MOD_INT.addMod(f[d], -f[i]);
      }
    }

    return IntStream.rangeClosed(1, maxValue)
        .map(d -> MOD_INT.multiplyMod(d, f[d]))
        .reduce(MOD_INT::addMod)
        .getAsInt();
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[Integer.highestOneBit(size) * 2 + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] = Solution.MOD_INT.addMod(a[pos], delta);
      pos += pos & -pos;
    }
  }

  int computePrefixSum(int pos) {
    int result = 0;
    while (pos != 0) {
      result = Solution.MOD_INT.addMod(result, a[pos]);
      pos -= pos & -pos;
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
    return (int) (x % modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, modulus);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, modulus);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, int exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
