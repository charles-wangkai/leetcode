class Solution {
  public long flowerGame(int n, int m) {
    int evenNum1 = n / 2;
    int oddNum1 = n - evenNum1;
    int evenNum2 = m / 2;
    int oddNum2 = m - evenNum2;

    return (long) evenNum1 * oddNum2 + (long) oddNum1 * evenNum2;
  }
}