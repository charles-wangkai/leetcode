import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 800;

  static int[] factorials;
  static int[] factorialInvs;

  static {
    factorials = new int[LIMIT + 1];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = multiplyMod(factorials[i - 1], i);
    }

    factorialInvs =
        Arrays.stream(factorials)
            .map(x -> BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue())
            .toArray();
  }

  public int countKReducibleNumbers(String s, int k) {
    int result = 0;
    for (int oneNum = 1; oneNum <= s.length(); ++oneNum) {
      if (computeReduceNum(oneNum) < k) {
        result = addMod(result, computeLessNum(s, oneNum));
      }
    }

    return result;
  }

  int computeLessNum(String s, int oneNum) {
    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '1') {
        result = addMod(result, CMod(s.length() - 1 - i, oneNum));

        if (oneNum == 0) {
          break;
        }
        --oneNum;
      }
    }

    return result;
  }

  int CMod(int n, int r) {
    return (r > n)
        ? 0
        : multiplyMod(factorials[n], multiplyMod(factorialInvs[r], factorialInvs[n - r]));
  }

  int computeReduceNum(int x) {
    return (x == 1) ? 0 : (1 + computeReduceNum(Integer.bitCount(x)));
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  static int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}