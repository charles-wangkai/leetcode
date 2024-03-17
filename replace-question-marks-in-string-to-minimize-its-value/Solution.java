import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public String minimizeStringValue(String s) {
    int[] counts = new int[ALPHABET_SIZE];
    for (char c : s.toCharArray()) {
      if (c != '?') {
        ++counts[c - 'a'];
      }
    }

    int questionMarkNum = (int) s.chars().filter(c -> c == '?').count();
    List<Character> replacements = new ArrayList<>();
    for (int i = 0; i < questionMarkNum; ++i) {
      int index =
          IntStream.range(0, counts.length)
              .boxed()
              .min(Comparator.<Integer, Integer>comparing(j -> counts[j]).thenComparing(j -> j))
              .get();
      ++counts[index];
      replacements.add((char) (index + 'a'));
    }
    Collections.sort(replacements);

    StringBuilder result = new StringBuilder();
    int replacementIndex = 0;
    for (char c : s.toCharArray()) {
      if (c == '?') {
        result.append(replacements.get(replacementIndex));
        ++replacementIndex;
      } else {
        result.append(c);
      }
    }

    return result.toString();
  }
}