import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int countDifferentSubsequenceGCDs(int[] nums) {
    Integer[] gcds = new Integer[Arrays.stream(nums).max().getAsInt() + 1];

    for (int num : nums) {
      for (int divisor : buildDivisors(num)) {
        gcds[divisor] =
            (gcds[divisor] == null) ? (num / divisor) : computeGCD(gcds[divisor], num / divisor);
      }
    }

    return (int) Arrays.stream(gcds).filter(gcd -> gcd != null && gcd == 1).count();
  }

  List<Integer> buildDivisors(int x) {
    List<Integer> divisors = new ArrayList<>();
    for (int i = 1; i * i <= x; ++i) {
      if (x % i == 0) {
        divisors.add(i);
        divisors.add(x / i);
      }
    }

    return divisors;
  }

  int computeGCD(int x, int y) {
    return (y == 0) ? x : computeGCD(y, x % y);
  }
}
