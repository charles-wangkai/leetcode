class Solution {
  public int[] divisibilityArray(String word, int m) {
    int[] result = new int[word.length()];
    int remainder = 0;
    for (int i = 0; i < result.length; ++i) {
      remainder = (int) ((remainder * 10L + (word.charAt(i) - '0')) % m);
      result[i] = (remainder == 0) ? 1 : 0;
    }

    return result;
  }
}
