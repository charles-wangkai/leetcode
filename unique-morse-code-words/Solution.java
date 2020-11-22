import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  static final String[] MORSES = {
    ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
    "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
  };

  public int uniqueMorseRepresentations(String[] words) {
    return (int)
        Arrays.stream(words)
            .map(
                word ->
                    word.chars()
                        .mapToObj(letter -> MORSES[letter - 'a'])
                        .collect(Collectors.joining()))
            .distinct()
            .count();
  }
}
