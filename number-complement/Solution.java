class Solution {
  public int findComplement(int num) {
    return (int) (Integer.highestOneBit(num) * 2L - 1 - num);
  }
}
