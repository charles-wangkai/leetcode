class Solution {
  public int maximumNumberOfStringPairs(String[] words) {
    int result = 0;
    for (int i = 0; i < words.length; ++i) {
      for (int j = i + 1; j < words.length; ++j) {
        if (new StringBuilder(words[i]).reverse().toString().equals(words[j])) {
          ++result;
        }
      }
    }

    return result;
  }
}
