class Solution {
  public long maximumSubsequenceCount(String text, String pattern) {
    long result = 0;
    int rightCount = (int) text.chars().filter(c -> c == pattern.charAt(1)).count();
    for (char c : text.toCharArray()) {
      if (c == pattern.charAt(1)) {
        --rightCount;
      }
      if (c == pattern.charAt(0)) {
        result += rightCount;
      }
    }

    result +=
        Math.max(
            text.chars().filter(c -> c == pattern.charAt(0)).count(),
            text.chars().filter(c -> c == pattern.charAt(1)).count());

    return result;
  }
}