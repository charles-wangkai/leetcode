import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> replaceNonCoprimes(int[] nums) {
    List<Integer> result = new ArrayList<>();
    for (int num : nums) {
      result.add(num);
      while (result.size() >= 2
          && gcd(result.get(result.size() - 1), result.get(result.size() - 2)) != 1) {
        int x = result.remove(result.size() - 1);
        int y = result.remove(result.size() - 1);
        result.add(lcm(x, y));
      }
    }

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  int lcm(int x, int y) {
    return (int) ((long) x * y / gcd(x, y));
  }
}