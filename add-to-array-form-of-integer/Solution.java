import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public List<Integer> addToArrayForm(int[] num, int k) {
    return new BigInteger(
            Arrays.stream(num).mapToObj(String::valueOf).collect(Collectors.joining()))
        .add(BigInteger.valueOf(k))
        .toString()
        .chars()
        .map(c -> c - '0')
        .boxed()
        .toList();
  }
}
