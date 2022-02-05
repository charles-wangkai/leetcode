import java.util.stream.IntStream;

class Solution {
  public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
    int minute = targetSeconds / 60;
    int second = targetSeconds % 60;

    int result = Integer.MAX_VALUE;
    if (minute <= 99) {
      result = Math.min(result, computeMinCost(startAt, moveCost, pushCost, minute, second));
    }
    if (minute != 0 && second + 60 <= 99) {
      result =
          Math.min(result, computeMinCost(startAt, moveCost, pushCost, minute - 1, second + 60));
    }

    return result;
  }

  static int computeMinCost(int startAt, int moveCost, int pushCost, int minute, int second) {
    String s = String.format("%02d%02d", minute, second);
    int result = computeCost(startAt, moveCost, pushCost, s);
    while (s.startsWith("0")) {
      s = s.substring(1);
      result = Math.min(result, computeCost(startAt, moveCost, pushCost, s));
    }

    return result;
  }

  static int computeCost(int startAt, int moveCost, int pushCost, String s) {
    return IntStream.range(0, s.length())
            .map(
                i -> (s.charAt(i) == ((i == 0) ? (startAt + '0') : s.charAt(i - 1))) ? 0 : moveCost)
            .sum()
        + pushCost * s.length();
  }
}