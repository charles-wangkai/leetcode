class Solution {
  public int secondsBetweenTimes(String startTime, String endTime) {
    return toSeconds(endTime) - toSeconds(startTime);
  }

  int toSeconds(String time) {
    int hour = Integer.parseInt(time.substring(0, 2));
    int minute = Integer.parseInt(time.substring(3, 5));
    int second = Integer.parseInt(time.substring(6));

    return hour * 60 * 60 + minute * 60 + second;
  }
}