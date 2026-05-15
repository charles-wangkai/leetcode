import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public int[] countWordOccurrences(String[] chunks, String[] queries) {
    String[] parts =
        Arrays.stream(chunks)
            .collect(Collectors.joining())
            .replaceAll("(?<=[a-z])-(?=[a-z])", "_")
            .split(" |-");

    Map<String, Integer> wordToCount = new HashMap<>();
    for (String part : parts) {
      if (!part.isEmpty()) {
        String word = part.replace('_', '-');
        wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
      }
    }

    return Arrays.stream(queries).mapToInt(query -> wordToCount.getOrDefault(query, 0)).toArray();
  }
}