class Solution {
  public String makeSmallestPalindrome(String s) {
    char[] letters = s.toCharArray();
    for (int i = 0, j = letters.length - 1; i < j; ++i, --j) {
      if (letters[i] < letters[j]) {
        letters[j] = letters[i];
      } else if (letters[i] > letters[j]) {
        letters[i] = letters[j];
      }
    }

    return new String(letters);
  }
}
