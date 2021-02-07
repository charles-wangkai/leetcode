class Solution {
  public String largestMerge(String word1, String word2) {
    StringBuilder result = new StringBuilder();
    while (!word1.isEmpty() || !word2.isEmpty()) {
      if (word1.compareTo(word2) >= 0) {
        result.append(word1.charAt(0));
        word1 = word1.substring(1);
      } else {
        result.append(word2.charAt(0));
        word2 = word2.substring(1);
      }
    }

    return result.toString();
  }
}
