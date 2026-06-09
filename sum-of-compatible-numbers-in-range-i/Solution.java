import java.util.stream.IntStream;

class Solution {
  public int sumOfGoodIntegers(int n, int k) {
    return IntStream.rangeClosed(n - k, n + k).filter(x -> x > 0 && (n & x) == 0).sum();
  }
}