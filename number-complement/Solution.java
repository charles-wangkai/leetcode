class Solution {
  public int findComplement(int num) {
    return (int) ((1L << Integer.toBinaryString(num).length()) - 1 - num);
  }
}
