import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[] numsSameConsecDiff(int n, int k) {
    List<Integer> solutions = new ArrayList<>();
    for (int digit = 1; digit <= 9; ++digit) {
      search(n, k, solutions, 1, digit);
    }

    return solutions.stream().mapToInt(x -> x).toArray();
  }

  void search(int n, int k, List<Integer> solutions, int length, int current) {
    if (length == n) {
      solutions.add(current);

      return;
    }

    int lastDigit = current % 10;
    if (lastDigit + k <= 9) {
      search(n, k, solutions, length + 1, current * 10 + (lastDigit + k));
    }

    if (k != 0 && lastDigit - k >= 0) {
      search(n, k, solutions, length + 1, current * 10 + (lastDigit - k));
    }
  }
}
