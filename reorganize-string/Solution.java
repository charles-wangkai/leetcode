import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public String reorganizeString(String s) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : s.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    Element[] elements =
        letterToCount.keySet().stream()
            .map(letter -> new Element(letter, letterToCount.get(letter)))
            .sorted(Comparator.comparing(Element::count).reversed())
            .toArray(Element[]::new);

    char[] result = new char[s.length()];
    int[] indices =
        IntStream.concat(
                IntStream.range(0, s.length()).filter(i -> i % 2 == 0),
                IntStream.range(0, s.length()).filter(i -> i % 2 == 1))
            .toArray();
    int index = 0;
    for (Element element : elements) {
      for (int i = 0; i < element.count(); ++i) {
        result[indices[index]] = element.letter();
        ++index;
      }
    }

    return (IntStream.range(0, result.length - 1).allMatch(i -> result[i] != result[i + 1]))
        ? new String(result)
        : "";
  }
}

record Element(char letter, int count) {}
