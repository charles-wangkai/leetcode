class Solution {
  public int numberOfCuts(int n) {
    return (n == 1 || n % 2 == 0) ? (n / 2) : n;
  }
}
