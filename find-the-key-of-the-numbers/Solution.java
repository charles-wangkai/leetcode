class Solution {
  public int generateKey(int num1, int num2, int num3) {
    return (num1 == 0 && num2 == 0 && num3 == 0)
        ? 0
        : (generateKey(num1 / 10, num2 / 10, num3 / 10) * 10
            + Math.min(Math.min(num1 % 10, num2 % 10), num3 % 10));
  }
}