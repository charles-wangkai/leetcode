import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public int[] minDifference(int n, int k) {
    List<Integer> divisors = new ArrayList<>();
    for (int i = 1; i * i <= n; ++i) {
      if (n % i == 0) {
        divisors.add(i);
        if (n / i != i) {
          divisors.add(n / i);
        }
      }
    }
    Collections.sort(divisors);

    return search(n, divisors, new int[k], 0, 1).factors();
  }

  Outcome search(int n, List<Integer> divisors, int[] factors, int index, int product) {
    if (index == factors.length) {
      return (product == n)
          ? new Outcome(factors[factors.length - 1] - factors[0], factors.clone())
          : new Outcome(Integer.MAX_VALUE, null);
    }

    Outcome result = new Outcome(Integer.MAX_VALUE, null);
    for (int divisor : divisors) {
      if (n / product % divisor == 0 && (index == 0 || divisor >= factors[index - 1])) {
        factors[index] = divisor;
        result = merge(result, search(n, divisors, factors, index + 1, product * divisor));
      }
    }

    return result;
  }

  Outcome merge(Outcome o1, Outcome o2) {
    return (o1.diff() <= o2.diff()) ? o1 : o2;
  }
}

record Outcome(int diff, int[] factors) {}
