import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> maxPrimes(int n, int s) {
    List<Integer> result = new ArrayList<>();
    int sum = 0;
    int prime = 1;
    while (true) {
      prime = findNextPrime(prime);
      if (prime > n || sum + prime > s) {
        break;
      }

      result.add(prime);
      sum += prime;
    }

    return result;
  }

  int findNextPrime(int x) {
    while (true) {
      ++x;
      if (isPrime(x)) {
        return x;
      }
    }
  }

  boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}