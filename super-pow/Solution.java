import java.math.BigInteger;
import java.util.Arrays;

public class Solution {
  private static final int MODULUS = 1337;

  public int superPow(int a, int[] b) {
    BigInteger exponent =
        new BigInteger(
            Arrays.stream(b)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString());

    int result = 1;
    while (!exponent.equals(BigInteger.ZERO)) {
      if (exponent.testBit(0)) {
        result = multiplyMod(result, a);
      }

      a = multiplyMod(a, a);
      exponent = exponent.shiftRight(1);
    }
    return result;
  }

  private int multiplyMod(int x, int y) {
    return (x % MODULUS) * (y % MODULUS) % MODULUS;
  }
}
