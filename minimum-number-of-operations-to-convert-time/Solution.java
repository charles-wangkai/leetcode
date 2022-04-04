class Solution {
  public int convertTime(String current, String correct) {
    int result = 0;
    int rest = toMinute(correct) - toMinute(current);
    for (int unit : new int[] {60, 15, 5, 1}) {
      result += rest / unit;
      rest %= unit;
    }

    return result;
  }

  int toMinute(String time) {
    return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
  }
}