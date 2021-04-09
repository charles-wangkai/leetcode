import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean isAlienSorted(String[] words, String order) {
    Map<Character, Character> translation = buildTranslation(order);
    String[] translated =
        Arrays.stream(words).map(word -> translate(translation, word)).toArray(String[]::new);

    return IntStream.range(0, translated.length - 1)
        .allMatch(i -> translated[i].compareTo(translated[i + 1]) <= 0);
  }

  Map<Character, Character> buildTranslation(String order) {
    return IntStream.range(0, order.length())
        .boxed()
        .collect(Collectors.toMap(order::charAt, i -> (char) ('a' + i)));
  }

  String translate(Map<Character, Character> translation, String word) {
    return word.chars()
        .mapToObj(ch -> String.valueOf(translation.get((char) ch)))
        .collect(Collectors.joining());
  }
}
