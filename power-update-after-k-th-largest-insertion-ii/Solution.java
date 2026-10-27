import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public List<Integer> powerUpdate(int[] nums, int p, int[][] queries) {
    int[] sortedValues =
        IntStream.concat(Arrays.stream(nums), Arrays.stream(queries).mapToInt(query -> query[0]))
            .sorted()
            .distinct()
            .toArray();
    Map<Integer, Integer> valueToCompressed =
        IntStream.range(0, sortedValues.length)
            .boxed()
            .collect(Collectors.toMap(i -> sortedValues[i], i -> i + 1));

    FenwickTree fenwickTree = new FenwickTree(sortedValues.length);
    for (int num : nums) {
      fenwickTree.add(valueToCompressed.get(num), 1);
    }

    List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      int val = query[0];
      int k = query[1];

      fenwickTree.add(valueToCompressed.get(val), 1);

      p = MOD_INT.powMod(p, findKthLargest(sortedValues, fenwickTree, k));
      result.add(p);
    }

    return result;
  }

  int findKthLargest(int[] sortedValues, FenwickTree fenwickTree, int k) {
    int index = -1;
    int lower = 0;
    int upper = sortedValues.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (fenwickTree.computePrefixSum(sortedValues.length) - fenwickTree.computePrefixSum(middle)
          >= k) {
        index = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return sortedValues[index];
  }
}

class FenwickTree {
  int[] a;

  FenwickTree(int size) {
    a = new int[size + 1];
  }

  void add(int pos, int delta) {
    while (pos < a.length) {
      a[pos] += delta;
      pos += pos & -pos;
    }
  }

  int computePrefixSum(int pos) {
    int result = 0;
    while (pos != 0) {
      result += a[pos];
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
