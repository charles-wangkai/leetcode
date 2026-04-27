import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public String sortVowels(String s) {
    Map<Character, List<Integer>> vowelToIndices = new HashMap<>();
    List<Integer> vowelIndices = new ArrayList<>();
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      if (isVowel(letter)) {
        vowelToIndices.putIfAbsent(letter, new ArrayList<>());
        vowelToIndices.get(letter).add(i);

        vowelIndices.add(i);
      }
    }

    Character[] sortedVowels =
        vowelIndices.stream()
            .map(s::charAt)
            .sorted(
                Comparator.comparing(vowel -> vowelToIndices.get(vowel).size())
                    .reversed()
                    .thenComparing(vowel -> vowelToIndices.get(vowel).getFirst()))
            .toArray(Character[]::new);

    char[] result = s.toCharArray();
    for (int i = 0; i < vowelIndices.size(); ++i) {
      result[vowelIndices.get(i)] = sortedVowels[i];
    }

    return String.valueOf(result);
  }

  boolean isVowel(char letter) {
    return "aeiou".indexOf(letter) != -1;
  }
}