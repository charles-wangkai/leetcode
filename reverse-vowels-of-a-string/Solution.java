import java.util.stream.IntStream;

class Solution {
  public String reverseVowels(String s) {
    char[] letters = s.toCharArray();
    int[] vowelIndices = IntStream.range(0, s.length()).filter(i -> isVowel(letters[i])).toArray();
    for (int i = 0, j = vowelIndices.length - 1; i < j; ++i, --j) {
      char temp = letters[vowelIndices[i]];
      letters[vowelIndices[i]] = letters[vowelIndices[j]];
      letters[vowelIndices[j]] = temp;
    }

    return new String(letters);
  }

  boolean isVowel(char letter) {
    return "aeiou".indexOf(Character.toLowerCase(letter)) >= 0;
  }
}
