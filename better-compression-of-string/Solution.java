import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public String betterCompression(String compressed) {
    Map<Character, Integer> letterToFreq = new HashMap<>();
    int index = 0;
    while (index != compressed.length()) {
      int endIndex = index + 1;
      while (endIndex != compressed.length() - 1
          && Character.isDigit(compressed.charAt(endIndex + 1))) {
        ++endIndex;
      }

      char letter = compressed.charAt(index);
      int freq = Integer.parseInt(compressed.substring(index + 1, endIndex + 1));
      letterToFreq.put(letter, letterToFreq.getOrDefault(letter, 0) + freq);

      index = endIndex + 1;
    }

    return letterToFreq.keySet().stream()
        .sorted()
        .map(letter -> "%c%d".formatted(letter, letterToFreq.get(letter)))
        .collect(Collectors.joining());
  }
}