import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public long[] resultArray(int[] nums, int k) {
    int[] remainders = Arrays.stream(nums).map(x -> x % k).toArray();

    return IntStream.range(0, k).mapToLong(x -> computeWayNum(k, remainders, x)).toArray();
  }

  long computeWayNum(int k, int[] remainders, int x) {
    if (x == 0) {
      if (k == 4) {
        int[] leftCounts = new int[remainders.length];
        int leftCount = 0;
        for (int i = 0; i < leftCounts.length; ++i) {
          leftCounts[i] = leftCount;
          if (remainders[i] % 2 == 1) {
            ++leftCount;
          } else {
            leftCount = 0;
          }
        }

        int[] evenIndices =
            IntStream.range(0, remainders.length).filter(i -> remainders[i] % 2 == 0).toArray();

        long result = 0;
        for (int i = 0; i < evenIndices.length; ++i) {
          if (remainders[evenIndices[i]] % 4 == 0) {
            result +=
                (leftCounts[evenIndices[i]] + 1L)
                    * (((i == evenIndices.length - 1) ? remainders.length : evenIndices[i + 1])
                        - evenIndices[i]);
          }
          if (i != evenIndices.length - 1) {
            result += (leftCounts[evenIndices[i]] + 1L) * (remainders.length - evenIndices[i + 1]);
          }
        }

        return result;
      }

      long result = remainders.length * (remainders.length + 1L) / 2;
      int length = 0;
      for (int i = 0; i <= remainders.length; ++i) {
        if (i != remainders.length && remainders[i] != 0) {
          ++length;
        } else {
          result -= length * (length + 1L) / 2;
          length = 0;
        }
      }

      return result;
    }

    if (k == 4 && x == 2) {
      int[] leftCounts = new int[remainders.length];
      int leftCount = 0;
      for (int i = 0; i < leftCounts.length; ++i) {
        leftCounts[i] = leftCount;
        if (remainders[i] % 2 == 1) {
          ++leftCount;
        } else {
          leftCount = 0;
        }
      }

      int[] rightCounts = new int[remainders.length];
      int rightCount = 0;
      for (int i = rightCounts.length - 1; i >= 0; --i) {
        rightCounts[i] = rightCount;
        if (remainders[i] % 2 == 1) {
          ++rightCount;
        } else {
          rightCount = 0;
        }
      }

      return IntStream.range(0, remainders.length)
          .filter(i -> remainders[i] == 2)
          .mapToLong(i -> (leftCounts[i] + 1L) * (rightCounts[i] + 1))
          .sum();
    }

    int xInv = BigInteger.valueOf(x).modInverse(BigInteger.valueOf(k)).intValue();

    long result = 0;
    Map<Integer, Integer> prefixProductToCount = new HashMap<>();
    prefixProductToCount.put(1, 1);
    int prefixProduct = 1;
    for (int remainder : remainders) {
      if (remainder == 0 || (k == 4 && remainder == 2)) {
        prefixProductToCount.clear();
        prefixProductToCount.put(1, 1);
        prefixProduct = 1;
      } else {
        prefixProduct = multiplyMod(prefixProduct, remainder, k);
        result += prefixProductToCount.getOrDefault(multiplyMod(prefixProduct, xInv, k), 0);

        prefixProductToCount.put(
            prefixProduct, prefixProductToCount.getOrDefault(prefixProduct, 0) + 1);
      }
    }

    return result;
  }

  int multiplyMod(int x, int y, int m) {
    return x * y % m;
  }
}
