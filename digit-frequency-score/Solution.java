class Solution {
  public int digitFrequencyScore(int n) {
    return String.valueOf(n).chars().map(c -> c - '0').sum();
  }
}