class Solution {
  public String removeTrailingZeros(String num) {
    while (num.endsWith("0")) {
      num = num.substring(0, num.length() - 1);
    }

    return num;
  }
}
