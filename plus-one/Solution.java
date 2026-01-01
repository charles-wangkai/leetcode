import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public int[] plusOne(int[] digits) {
    return new BigInteger(
            Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining()))
        .add(BigInteger.ONE)
        .toString()
        .chars()
        .map(c -> c - '0')
        .toArray();
  }
}
