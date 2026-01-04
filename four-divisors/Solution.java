import java.util.ArrayList;
import java.util.List;

class Solution {
  public int sumFourDivisors(int[] nums) {
    int result = 0;
    for (int x : nums) {
      List<Integer> divisors = buildDivisors(x);
      if (divisors.size() == 4) {
        result += divisors.stream().mapToInt(Integer::intValue).sum();
      }
    }

    return result;
  }

  List<Integer> buildDivisors(int x) {
    List<Integer> result = new ArrayList<>();
    for (int i = 1; i * i <= x; ++i) {
      if (x % i == 0) {
        result.add(i);

        if (x / i != i) {
          result.add(x / i);
        }
      }
    }

    return result;
  }
}