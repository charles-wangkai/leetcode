import java.util.ArrayList;
import java.util.List;

public class Solution {
  public List<List<Integer>> getFactors(int n) {
    return getFactors(n, 2, false);
  }

  List<List<Integer>> getFactors(int number, int start, boolean allowSelf) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = start; i * i <= number; i++) {
      if (number % i == 0) {
        List<List<Integer>> subResult = getFactors(number / i, i, true);
        for (List<Integer> oneSubResult : subResult) {
          List<Integer> factors = new ArrayList<>();
          factors.add(i);
          factors.addAll(oneSubResult);
          result.add(factors);
        }
      }
    }
    if (allowSelf && number >= start) {
      List<Integer> factors = new ArrayList<>();
      factors.add(number);
      result.add(factors);
    }
    return result;
  }
}
