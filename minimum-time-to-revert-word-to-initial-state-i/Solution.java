class Solution {
  public int minimumTimeToInitialState(String word, int k) {
    int result = 1;
    int beginIndex = k;
    while (!word.startsWith(word.substring(Math.min(word.length(), beginIndex)))) {
      ++result;
      beginIndex += k;
    }

    return result;
  }
}