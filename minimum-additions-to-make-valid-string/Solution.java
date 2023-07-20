class Solution {
  static final String TARGET = "abc";

  public int addMinimum(String word) {
    int result = 0;
    int index = 0;
    while (index != word.length()) {
      for (int i = 0; i < TARGET.length(); ++i) {
        if (index == word.length() || word.charAt(index) != TARGET.charAt(i)) {
          ++result;
        } else {
          ++index;
        }
      }
    }

    return result;
  }
}
