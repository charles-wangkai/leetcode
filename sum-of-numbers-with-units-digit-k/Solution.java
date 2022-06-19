import java.util.stream.IntStream;

class Solution {
  public int minimumNumbers(int num, int k) {
    return (num == 0)
        ? 0
        : IntStream.rangeClosed(1, 10)
            .filter(i -> i * k <= num && (num - i * k) % 10 == 0)
            .min()
            .orElse(-1);
  }
}