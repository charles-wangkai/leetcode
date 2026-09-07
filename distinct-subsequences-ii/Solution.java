import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int distinctSubseqII(String s) {
    Map<Character, Integer> lastLetterToWayNum = new HashMap<>();
    for (char letter : s.toCharArray()) {
      lastLetterToWayNum.put(letter, MOD_INT.addMod(computeWayNumSum(lastLetterToWayNum), 1));
    }

    return computeWayNumSum(lastLetterToWayNum);
  }

  int computeWayNumSum(Map<Character, Integer> lastLetterToWayNum) {
    return lastLetterToWayNum.values().stream().reduce(0, MOD_INT::addMod);
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
