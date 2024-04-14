import java.util.stream.IntStream;

class Solution {
  public String findLatestTime(String s) {
    for (int minutes = 11 * 60 + 59; ; --minutes) {
      String time = String.format("%02d:%02d", minutes / 60, minutes % 60);
      if (IntStream.range(0, time.length())
          .allMatch(i -> time.charAt(i) == s.charAt(i) || s.charAt(i) == '?')) {
        return time;
      }
    }
  }
}