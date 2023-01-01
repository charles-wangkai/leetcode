class Solution {
  public int countDigits(int num) {
    return (int) String.valueOf(num).chars().filter(c -> num % (c - '0') == 0).count();
  }
}
