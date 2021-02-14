import java.util.stream.IntStream;

class Solution {
  public int minOperations(String s) {
    return Math.min(computeOperationNum(s, '0'), computeOperationNum(s, '1'));
  }

  int computeOperationNum(String s, char evenTarget) {
    return (int)
        IntStream.range(0, s.length())
            .filter(i -> (i % 2 == 0) != (s.charAt(i) == evenTarget))
            .count();
  }
}
