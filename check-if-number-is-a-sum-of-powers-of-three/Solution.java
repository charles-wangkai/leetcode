class Solution {
  public boolean checkPowersOfThree(int n) {
    if (n == 0) {
      return true;
    }
    if (n % 3 == 2) {
      return false;
    }

    return checkPowersOfThree(n / 3);
  }
}
