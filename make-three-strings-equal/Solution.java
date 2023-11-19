class Solution {
  public int findMinimumOperations(String s1, String s2, String s3) {
    int length = 0;
    while (length + 1 <= s1.length()
        && length + 1 <= s2.length()
        && length + 1 <= s3.length()
        && s1.substring(0, length + 1).equals(s2.substring(0, length + 1))
        && s2.substring(0, length + 1).equals(s3.substring(0, length + 1))) {
      ++length;
    }

    return (length == 0) ? -1 : (s1.length() + s2.length() + s3.length() - 3 * length);
  }
}
