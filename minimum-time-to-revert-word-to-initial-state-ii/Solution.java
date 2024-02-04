class Solution {
  static final int BASE_1 = 31;
  static final int BASE_2 = 37;

  public int minimumTimeToInitialState(String word, int k) {
    long[] prefixHashs1 = new long[word.length()];
    long prefixHash1 = 0;
    long[] prefixHashs2 = new long[word.length()];
    long prefixHash2 = 0;
    for (int i = 0; i < word.length(); ++i) {
      prefixHash1 = prefixHash1 * BASE_1 + (word.charAt(i) - 'a');
      prefixHashs1[i] = prefixHash1;

      prefixHash2 = prefixHash2 * BASE_2 + (word.charAt(i) - 'a');
      prefixHashs2[i] = prefixHash2;
    }

    long[] suffixHashs1 = new long[word.length()];
    long suffixHash1 = 0;
    long[] suffixHashs2 = new long[word.length()];
    long suffixHash2 = 0;
    long placeValue1 = 1;
    long placeValue2 = 1;
    for (int i = word.length() - 1; i >= 0; --i) {
      suffixHash1 += (word.charAt(i) - 'a') * placeValue1;
      suffixHashs1[i] = suffixHash1;

      suffixHash2 += (word.charAt(i) - 'a') * placeValue2;
      suffixHashs2[i] = suffixHash2;

      placeValue1 *= BASE_1;
      placeValue2 *= BASE_2;
    }

    int result = 1;
    int beginIndex = k;
    while (beginIndex < word.length()
        && !(suffixHashs1[beginIndex] == prefixHashs1[word.length() - 1 - beginIndex]
            && suffixHashs2[beginIndex] == prefixHashs2[word.length() - 1 - beginIndex])) {
      ++result;
      beginIndex += k;
    }

    return result;
  }
}