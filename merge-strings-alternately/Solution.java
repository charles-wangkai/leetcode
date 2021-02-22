class Solution {
  public String mergeAlternately(String word1, String word2) {
    StringBuilder result = new StringBuilder();
    int index1 = 0;
    int index2 = 0;
    while (index1 != word1.length() || index2 != word2.length()) {
      if (index2 == word2.length() || (index1 != word1.length() && result.length() % 2 == 0)) {
        result.append(word1.charAt(index1));
        ++index1;
      } else {
        result.append(word2.charAt(index2));
        ++index2;
      }
    }

    return result.toString();
  }
}
