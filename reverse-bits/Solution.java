class Solution {
  public int reverseBits(int n) {
    String s = new StringBuilder(Integer.toBinaryString(n)).reverse().toString();

    return Integer.parseInt("%s%s".formatted(s, "0".repeat(32 - s.length())), 2);
  }
}
