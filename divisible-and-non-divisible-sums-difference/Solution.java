import java.util.stream.IntStream;

class Solution {
  public int differenceOfSums(int n, int m) {
    return IntStream.rangeClosed(1, n).map(i -> ((i % m == 0) ? -1 : 1) * i).sum();
  }
}
