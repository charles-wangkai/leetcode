import java.util.stream.IntStream;

class Solution {
  public int countPrimeSetBits(int left, int right) {
    return (int)
        IntStream.rangeClosed(left, right).filter(x -> isPrime(Integer.bitCount(x))).count();
  }

  boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }

    for (int i = 2; i * i <= n; ++i) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }
}
