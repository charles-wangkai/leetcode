import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countBalls(int lowLimit, int highLimit) {
    Map<Integer, Integer> digitSumToCount = new HashMap<>();
    for (int i = lowLimit; i <= highLimit; ++i) {
      int digitSum = String.valueOf(i).chars().map(ch -> ch - '0').sum();
      digitSumToCount.put(digitSum, digitSumToCount.getOrDefault(digitSum, 0) + 1);
    }

    return digitSumToCount.values().stream().mapToInt(x -> x).max().getAsInt();
  }
}
