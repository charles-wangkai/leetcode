import java.util.stream.IntStream;

class Solution {
  public int numberOfRounds(String startTime, String finishTime) {
    boolean[] minutes = new boolean[24 * 60];
    int startMinute = convertToMinutes(startTime);
    int finishMinute = convertToMinutes(finishTime);
    if (startMinute < finishMinute) {
      for (int i = startMinute; i <= finishMinute; ++i) {
        minutes[i] = true;
      }
    } else {
      for (int i = startMinute; i < minutes.length; ++i) {
        minutes[i] = true;
      }
      for (int i = 0; i <= finishMinute; ++i) {
        minutes[i] = true;
      }
    }

    int result = 0;
    int roundBegin = 0;
    while (roundBegin != minutes.length) {
      int roundEnd = roundBegin + 15;

      if (IntStream.rangeClosed(roundBegin, roundEnd)
          .allMatch(i -> (i == minutes.length) ? (startMinute > finishMinute) : minutes[i])) {
        ++result;
      }

      roundBegin = roundEnd;
    }
    result = Math.min(minutes.length / 15 - 1, result);

    return result;
  }

  int convertToMinutes(String time) {
    int hour = Integer.parseInt(time.substring(0, 2));
    int minute = Integer.parseInt(time.substring(3));

    return hour * 60 + minute;
  }
}
