import java.util.stream.IntStream;

class Solution {
  public boolean isBalanced(String num) {
    return IntStream.range(0, num.length())
            .filter(i -> i % 2 == 0)
            .map(i -> num.charAt(i) - '0')
            .sum()
        == IntStream.range(0, num.length())
            .filter(i -> i % 2 == 1)
            .map(i -> num.charAt(i) - '0')
            .sum();
  }
}