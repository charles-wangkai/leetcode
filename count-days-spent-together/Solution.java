class Solution {
  static final int[] DAY_NUMS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public int countDaysTogether(
      String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
    int result = 0;
    for (int m = 1; m <= DAY_NUMS.length; ++m) {
      for (int d = 1; d <= DAY_NUMS[m - 1]; ++d) {
        String date = String.format("%02d-%02d", m, d);
        if (date.compareTo(arriveAlice) >= 0
            && date.compareTo(leaveAlice) <= 0
            && date.compareTo(arriveBob) >= 0
            && date.compareTo(leaveBob) <= 0) {
          ++result;
        }
      }
    }

    return result;
  }
}