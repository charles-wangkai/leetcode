class Solution {
  public boolean haveConflict(String[] event1, String[] event2) {
    int beginTime1 = toMinutes(event1[0]);
    int endTime1 = toMinutes(event1[1]);
    int beginTime2 = toMinutes(event2[0]);
    int endTime2 = toMinutes(event2[1]);

    return !(beginTime1 > endTime2 || endTime1 < beginTime2);
  }

  int toMinutes(String time) {
    return 60 * Integer.parseInt(time.substring(0, 2)) + Integer.parseInt(time.substring(3));
  }
}
