import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String smallestEquivalentString(String s1, String s2, String baseStr) {
    Map<Character, Character> letterToParent =
        IntStream.rangeClosed('a', 'z')
            .mapToObj(c -> (char) c)
            .collect(Collectors.toMap(Function.identity(), Function.identity()));

    for (int i = 0; i < s1.length(); ++i) {
      char root1 = findRoot(letterToParent, s1.charAt(i));
      char root2 = findRoot(letterToParent, s2.charAt(i));
      if (root1 < root2) {
        letterToParent.put(root2, root1);
      } else if (root1 > root2) {
        letterToParent.put(root1, root2);
      }
    }

    return baseStr
        .chars()
        .mapToObj(c -> findRoot(letterToParent, (char) c))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }

  char findRoot(Map<Character, Character> letterToParent, char letter) {
    if (letterToParent.get(letter) == letter) {
      return letter;
    }

    letterToParent.put(letter, findRoot(letterToParent, letterToParent.get(letter)));

    return letterToParent.get(letter);
  }
}
