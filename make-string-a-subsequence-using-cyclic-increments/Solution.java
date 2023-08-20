class Solution {
  public boolean canMakeSubsequence(String str1, String str2) {
    int index2 = 0;
    for (char c : str1.toCharArray()) {
      if (index2 != str2.length()
          && (c == str2.charAt(index2) || (c - 'a' + 1) % 26 + 'a' == str2.charAt(index2))) {
        ++index2;
      }
    }

    return index2 == str2.length();
  }
}
