import java.util.stream.IntStream;

class Solution {
  public int sumOfMultiples(int n) {
    return IntStream.rangeClosed(1, n).filter(x -> x % 3 == 0 || x % 5 == 0 || x % 7 == 0).sum();
  }
}
