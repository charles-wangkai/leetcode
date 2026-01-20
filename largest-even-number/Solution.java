class Solution {
  public String largestEven(String s) {
    int endIndex = s.length() - 1;
    while (endIndex != -1 && s.charAt(endIndex) == '1') {
      --endIndex;
    }

    return s.substring(0, endIndex + 1);
  }
}