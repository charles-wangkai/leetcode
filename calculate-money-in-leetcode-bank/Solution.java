class Solution {
  public int totalMoney(int n) {
    int result = 0;
    int lastMonday = 0;
    int last = -1;
    for (int i = 0; i < n; ++i) {
      int current;
      if (i % 7 == 0) {
        current = lastMonday + 1;
        lastMonday = current;
      } else {
        current = last + 1;
      }

      result += current;
      last = current;
    }

    return result;
  }
}
