class Solution {
  public int totalMoney(int n) {
    int result = 0;
    int money = 6;
    for (int i = 0; i < n; ++i) {
      if (i % 7 == 0) {
        money -= 5;
      } else {
        ++money;
      }

      result += money;
    }

    return result;
  }
}
