import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countLargestGroup(int n) {
    Map<Integer, Integer> digitSumToCount = new HashMap<>();
    for (int i = 1; i <= n; ++i) {
      int digitSum = String.valueOf(i).chars().map(c -> c - '0').sum();
      digitSumToCount.put(digitSum, digitSumToCount.getOrDefault(digitSum, 0) + 1);
    }

    int maxCount = digitSumToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();

    return (int)
        digitSumToCount.keySet().stream()
            .filter(digitSum -> digitSumToCount.get(digitSum) == maxCount)
            .count();
  }
}