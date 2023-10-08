import java.util.stream.IntStream;

class Solution {
  public int differenceOfSums(int n, int m) {
    return IntStream.rangeClosed(1, n).filter(i -> i % m != 0).sum()
        - IntStream.rangeClosed(1, n).filter(i -> i % m == 0).sum();
  }
}
