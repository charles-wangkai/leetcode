import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int maximumSum(int[] nums) {
    Map<Integer, List<Integer>> digitSumToValues = new HashMap<>();
    for (int value : nums) {
      int digitSum = String.valueOf(value).chars().map(c -> c - '0').sum();
      digitSumToValues.putIfAbsent(digitSum, new ArrayList<>());
      digitSumToValues.get(digitSum).add(value);
    }

    return digitSumToValues.values().stream()
        .filter(values -> values.size() >= 2)
        .mapToInt(
            values ->
                values.stream()
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .mapToInt(Integer::intValue)
                    .sum())
        .max()
        .orElse(-1);
  }
}