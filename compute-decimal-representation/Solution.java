import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] decimalRepresentation(int n) {
    List<Integer> result = new ArrayList<>();
    for (int power = 1_000_000_000; power >= 1; power /= 10) {
      if (n >= power) {
        result.add(n / power * power);
        n %= power;
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}