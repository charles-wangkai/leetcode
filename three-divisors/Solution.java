import java.util.stream.IntStream;

class Solution {
  public boolean isThree(int n) {
    return IntStream.rangeClosed(1, n).filter(x -> n % x == 0).count() == 3;
  }
}
