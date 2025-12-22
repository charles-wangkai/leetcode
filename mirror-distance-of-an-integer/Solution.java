class Solution {
  public int mirrorDistance(int n) {
    return Math.abs(
        n - Integer.parseInt(new StringBuilder(String.valueOf(n)).reverse().toString()));
  }
}