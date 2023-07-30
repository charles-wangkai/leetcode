import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countSteppingNumbers(String low, String high) {
    return addMod(
        computeSteppingNum(new BigInteger(high).add(BigInteger.ONE).toString()),
        -computeSteppingNum(low));
  }

  int computeSteppingNum(String limit) {
    int[][] wayNums = new int[limit.length() + 1][10];
    Arrays.fill(wayNums[1], 1);
    for (int length = 2; length < wayNums.length; ++length) {
      for (int firstDigit = 0; firstDigit <= 9; ++firstDigit) {
        for (int d : new int[] {-1, 1}) {
          int prevFirstDigit = firstDigit + d;
          if (prevFirstDigit >= 0 && prevFirstDigit <= 9) {
            wayNums[length][firstDigit] =
                addMod(wayNums[length][firstDigit], wayNums[length - 1][prevFirstDigit]);
          }
        }
      }
    }

    int result = 0;
    for (int length = 1; length < limit.length(); ++length) {
      for (int firstDigit = 1; firstDigit <= 9; ++firstDigit) {
        result = addMod(result, wayNums[length][firstDigit]);
      }
    }
    for (int firstDigit = 1; firstDigit < limit.charAt(0) - '0'; ++firstDigit) {
      result = addMod(result, wayNums[limit.length()][firstDigit]);
    }
    for (int i = 1; i < limit.length(); ++i) {
      for (int d : new int[] {-1, 1}) {
        int firstDigit = limit.charAt(i - 1) - '0' + d;
        if (firstDigit >= 0 && firstDigit <= 9 && firstDigit + '0' < limit.charAt(i)) {
          result = addMod(result, wayNums[limit.length() - i][firstDigit]);
        }
      }

      if (limit.charAt(i) != limit.charAt(i - 1) - 1
          && limit.charAt(i) != limit.charAt(i - 1) + 1) {
        break;
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
