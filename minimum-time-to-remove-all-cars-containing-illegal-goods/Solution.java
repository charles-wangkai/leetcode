import java.util.stream.IntStream;

class Solution {
  public int minimumTime(String s) {
    int[] rightTimes = new int[s.length()];
    for (int i = rightTimes.length - 1; i >= 0; --i) {
      if (s.charAt(i) == '0') {
        rightTimes[i] = (i == rightTimes.length - 1) ? 0 : rightTimes[i + 1];
      } else {
        rightTimes[i] =
            Math.min(
                ((i == rightTimes.length - 1) ? 0 : rightTimes[i + 1]) + 2, rightTimes.length - i);
      }
    }

    return IntStream.rangeClosed(0, s.length())
        .map(i -> i + ((i == s.length()) ? 0 : rightTimes[i]))
        .min()
        .getAsInt();
  }
}