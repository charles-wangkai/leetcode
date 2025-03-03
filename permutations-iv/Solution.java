import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public int[] permute(int n, long k) {
    if (!isTotalNumEnough(n, k)) {
      return new int[0];
    }

    int[] result = new int[n];
    Set<Integer> seen = new HashSet<>();
    int evenRest = n / 2;
    int oddRest = n - evenRest;
    for (int i = 0; i < result.length; ++i) {
      for (int value = 1; ; ++value) {
        if (!seen.contains(value)
            && ((i == 0 && (n % 2 == 0 || value % 2 == 1))
                || (i != 0 && value % 2 != result[i - 1] % 2))) {
          BigInteger permutationNum = computePermutationNum(value, evenRest, oddRest);
          if (permutationNum.compareTo(BigInteger.valueOf(k)) >= 0) {
            result[i] = value;

            break;
          }

          k -= permutationNum.longValue();
        }
      }

      seen.add(result[i]);
      if (result[i] % 2 == 0) {
        --evenRest;
      } else {
        --oddRest;
      }
    }

    return result;
  }

  BigInteger computePermutationNum(int value, int evenRest, int oddRest) {
    return (value % 2 == 0)
        ? factorial(evenRest - 1).multiply(factorial(oddRest))
        : factorial(evenRest).multiply(factorial(oddRest - 1));
  }

  boolean isTotalNumEnough(int n, long k) {
    BigInteger totalNum;
    if (n % 2 == 0) {
      totalNum = factorial(n / 2).pow(2).multiply(BigInteger.valueOf(2));
    } else {
      totalNum = factorial(n / 2).multiply(factorial(n / 2 + 1));
    }

    return totalNum.compareTo(BigInteger.valueOf(k)) >= 0;
  }

  BigInteger factorial(int x) {
    BigInteger result = BigInteger.ONE;
    for (int i = 1; i <= x; ++i) {
      result = result.multiply(BigInteger.valueOf(i));
    }

    return result;
  }
}