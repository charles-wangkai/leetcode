class Solution {
  public String reversePrefix(String word, char ch) {
    char[] letters = word.toCharArray();
    for (int i = 0; i < letters.length; ++i) {
      if (letters[i] == ch) {
        for (int leftIndex = 0, rightIndex = i; leftIndex < rightIndex; ++leftIndex, --rightIndex) {
          char temp = letters[leftIndex];
          letters[leftIndex] = letters[rightIndex];
          letters[rightIndex] = temp;
        }

        break;
      }
    }

    return new String(letters);
  }
}
