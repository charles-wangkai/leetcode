class Solution {
  static final String ALPHABET = "aeiou";

  public int longestBeautifulSubstring(String word) {
    int result = 0;
    int prevIndex = -1;
    int length = 0;
    for (char ch : word.toCharArray()) {
      int index = ALPHABET.indexOf(ch);
      if (index == prevIndex || index == prevIndex + 1) {
        ++length;
        if (index == ALPHABET.length() - 1) {
          result = Math.max(result, length);
        }

        prevIndex = index;
      } else if (index == 0) {
        prevIndex = 0;
        length = 1;
      } else {
        prevIndex = -1;
        length = 0;
      }
    }

    return result;
  }
}
