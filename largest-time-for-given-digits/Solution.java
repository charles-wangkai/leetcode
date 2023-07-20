class Solution {
  int maxTotalMinute;
  String maxTime;

  public String largestTimeFromDigits(int[] A) {
    maxTotalMinute = -1;
    maxTime = "";

    search(A, 0);

    return maxTime;
  }

  void search(int[] digits, int index) {
    if (index == digits.length) {
      int hour = digits[0] * 10 + digits[1];
      int minute = digits[2] * 10 + digits[3];

      if (hour <= 23 && minute <= 59) {
        int totalMinute = hour * 60 + minute;

        if (totalMinute > maxTotalMinute) {
          maxTotalMinute = totalMinute;
          maxTime = String.format("%02d:%02d", hour, minute);
        }
      }

      return;
    }

    for (int i = index; i < digits.length; ++i) {
      swap(digits, index, i);
      search(digits, index + 1);
      swap(digits, index, i);
    }
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}
