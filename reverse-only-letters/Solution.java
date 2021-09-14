import java.util.stream.IntStream;

class Solution {
  public String reverseOnlyLetters(String s) {
    char[] letters = s.toCharArray();

    int[] letterIndices =
        IntStream.range(0, letters.length).filter(i -> Character.isLetter(letters[i])).toArray();

    for (int i = 0, j = letterIndices.length - 1; i < j; ++i, --j) {
      char temp = letters[letterIndices[i]];
      letters[letterIndices[i]] = letters[letterIndices[j]];
      letters[letterIndices[j]] = temp;
    }

    return new String(letters);
  }
}
