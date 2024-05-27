class Solution {
  public String compressedString(String word) {
    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index != word.length()) {
      char c = word.charAt(index);
      int count = 0;
      while (index != word.length() && word.charAt(index) == c && count != 9) {
        ++index;
        ++count;
      }

      result.append(count).append(c);
    }

    return result.toString();
  }
}