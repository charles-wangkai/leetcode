class Solution {
  public int smallestNumber(int n) {
    return Integer.parseInt(Integer.toBinaryString(n).replace('0', '1'), 2);
  }
}