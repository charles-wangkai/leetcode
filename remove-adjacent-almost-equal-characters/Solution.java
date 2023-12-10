class Solution {
  public int removeAlmostEqualCharacters(String word) {
    int result = 0;
    int index = 0;
    while (index < word.length() - 1) {
      if (Math.abs(word.charAt(index) - word.charAt(index + 1)) <= 1) {
        ++result;
        index += 2;
      } else {
        ++index;
      }
    }

    return result;
  }
}