import java.util.stream.IntStream;

class Solution {
  public int countTime(String time) {
    int result = 0;
    for (int hour = 0; hour <= 23; ++hour) {
      for (int minute = 0; minute <= 59; ++minute) {
        if (isMatched(time, String.format("%02d:%02d", hour, minute))) {
          ++result;
        }
      }
    }

    return result;
  }

  static boolean isMatched(String time, String replaced) {
    return IntStream.range(0, time.length())
        .allMatch(i -> time.charAt(i) == '?' || time.charAt(i) == replaced.charAt(i));
  }
}
