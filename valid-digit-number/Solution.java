class Solution {
  public boolean validDigit(int n, int x) {
    return String.valueOf(n).contains(String.valueOf(x))
        && !String.valueOf(n).startsWith(String.valueOf(x));
  }
}