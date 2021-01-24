import java.util.stream.IntStream;

class Solution {
  public String maximumTime(String time) {
    for (int h = 23; ; --h) {
      for (int m = 59; m >= 0; --m) {
        String t = String.format("%02d:%02d", h, m);
        if (isMatched(time, t)) {
          return t;
        }
      }
    }
  }

  boolean isMatched(String time, String t) {
    return IntStream.range(0, time.length())
        .allMatch(i -> time.charAt(i) == '?' || time.charAt(i) == t.charAt(i));
  }
}
