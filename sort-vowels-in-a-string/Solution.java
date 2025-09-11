import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public String sortVowels(String s) {
    int[] vowelIndices =
        IntStream.range(0, s.length())
            .filter(i -> "aeiou".contains(String.valueOf(Character.toLowerCase(s.charAt(i)))))
            .toArray();
    List<Character> sortedVowels =
        Arrays.stream(vowelIndices).mapToObj(s::charAt).sorted().toList();

    char[] letters = s.toCharArray();
    for (int i = 0; i < vowelIndices.length; ++i) {
      letters[vowelIndices[i]] = sortedVowels.get(i);
    }

    return String.valueOf(letters);
  }
}
