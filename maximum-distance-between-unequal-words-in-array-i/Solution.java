class Solution {
  public int maxDistance(String[] words) {
    int result = 0;
    for (int i = 0; i < words.length; ++i) {
      for (int j = i + 1; j < words.length; ++j) {
        if (!words[j].equals(words[i])) {
          result = Math.max(result, j - i + 1);
        }
      }
    }

    return result;
  }
}