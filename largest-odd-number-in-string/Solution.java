class Solution {
  public String largestOddNumber(String num) {
    int endIndex = num.length() - 1;
    while (endIndex != -1 && (num.charAt(endIndex) - '0') % 2 == 0) {
      --endIndex;
    }

    return num.substring(0, endIndex + 1);
  }
}
