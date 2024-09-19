import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int subsequenceCount(int[] nums) {
    int oddNum = (int) Arrays.stream(nums).filter(x -> x % 2 == 1).count();

    int[] factorials = new int[oddNum + 1];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = multiplyMod(factorials[i - 1], i);
    }

    int result = 0;
    for (int i = 1; i <= oddNum; i += 2) {
      result = addMod(result, CMod(factorials, oddNum, i));
    }
    for (int i = 0; i < nums.length - oddNum; ++i) {
      result = multiplyMod(result, 2);
    }

    return result;
  }

  int CMod(int[] factorials, int n, int r) {
    return multiplyMod(factorials[n], inverseMod(multiplyMod(factorials[r], factorials[n - r])));
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int inverseMod(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue();
  }
}