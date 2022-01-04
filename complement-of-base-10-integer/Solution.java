class Solution {
  public int bitwiseComplement(int n) {
    return (1 << Integer.toBinaryString(n).length()) - 1 - n;
  }
}
