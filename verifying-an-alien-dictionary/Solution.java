import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean isAlienSorted(String[] words, String order) {
    Map<Character, Character> translation =
        IntStream.range(0, order.length())
            .boxed()
            .collect(Collectors.toMap(order::charAt, i -> (char) ('a' + i)));
    String[] translated =
        Arrays.stream(words)
            .map(
                word ->
                    word.chars()
                        .mapToObj(c -> translation.get((char) c))
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
            .toArray(String[]::new);

    return IntStream.range(0, translated.length - 1)
        .allMatch(i -> translated[i].compareTo(translated[i + 1]) <= 0);
  }
}
