import java.math.BigInteger;
import java.util.List;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);
  static final int[] R_OFFSETS = {0, 1, 1};
  static final int[] C_OFFSETS = {1, 0, 1};

  public int[] pathsWithMaxScore(List<String> board) {
    int size = board.size();

    Element[][] elements = new Element[size][size];
    for (int r = size - 1; r >= 0; --r) {
      for (int c = size - 1; c >= 0; --c) {
        char square = board.get(r).charAt(c);

        int maxSum;
        int pathNum;
        if (square == 'S') {
          maxSum = 0;
          pathNum = 1;
        } else {
          maxSum = -1;
          pathNum = 0;

          if (square != 'X') {
            for (int i = 0; i < R_OFFSETS.length; ++i) {
              int adjR = r + R_OFFSETS[i];
              int adjC = c + C_OFFSETS[i];
              if (adjR < size && adjC < size) {
                maxSum = Math.max(maxSum, elements[adjR][adjC].maxSum());
              }
            }

            for (int i = 0; i < R_OFFSETS.length; ++i) {
              int adjR = r + R_OFFSETS[i];
              int adjC = c + C_OFFSETS[i];
              if (adjR < size && adjC < size && elements[adjR][adjC].maxSum() == maxSum) {
                pathNum = MOD_INT.addMod(pathNum, elements[adjR][adjC].pathNum());
              }
            }

            if (maxSum != -1 && Character.isDigit(square)) {
              maxSum += square - '0';
            }
          }
        }

        elements[r][c] = new Element(maxSum, pathNum);
      }
    }

    return (elements[0][0].maxSum() == -1)
        ? new int[] {0, 0}
        : new int[] {elements[0][0].maxSum(), elements[0][0].pathNum()};
  }
}

record Element(int maxSum, int pathNum) {}

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
