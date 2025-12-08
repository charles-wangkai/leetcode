import java.util.stream.IntStream;

class Solution {
  public boolean completePrime(int num) {
    String s = String.valueOf(num);

    return IntStream.rangeClosed(1, s.length())
        .allMatch(
            i ->
                isPrime(Integer.parseInt(s.substring(0, i)))
                    && isPrime(Integer.parseInt(s.substring(s.length() - i))));
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
}