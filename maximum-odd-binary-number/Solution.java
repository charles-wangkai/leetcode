class Solution {
  public String maximumOddBinaryNumber(String s) {
    int oneNum = (int) s.chars().filter(c -> c == '1').count();
    int zeroNum = s.length() - oneNum;

    return String.format("%s%s1", "1".repeat(oneNum - 1), "0".repeat(zeroNum));
  }
}
