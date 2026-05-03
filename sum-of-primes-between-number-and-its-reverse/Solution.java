import java.util.stream.IntStream;

class Solution {
  public int sumOfPrimesInRange(int n) {
    int[] sorted = IntStream.of(n, reverse(n)).sorted().toArray();

    return IntStream.rangeClosed(sorted[0], sorted[1]).filter(this::isPrime).sum();
  }

  boolean isPrime(int x) {
    if (x <= 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }

  int reverse(int n) {
    return Integer.parseInt(new StringBuilder(String.valueOf(n)).reverse().toString());
  }
}