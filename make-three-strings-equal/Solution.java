class Solution {
  public int findMinimumOperations(String s1, String s2, String s3) {
    int length = 0;
    while (length != s1.length()
        && length != s2.length()
        && length != s3.length()
        && s1.charAt(length) == s2.charAt(length)
        && s2.charAt(length) == s3.charAt(length)) {
      ++length;
    }

    return (length == 0) ? -1 : (s1.length() + s2.length() + s3.length() - 3 * length);
  }
}
