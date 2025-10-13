import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<String> removeAnagrams(String[] words) {
    String[] keys = Arrays.stream(words).map(this::buildKey).toArray(String[]::new);

    return IntStream.range(0, keys.length)
        .filter(i -> i == 0 || !keys[i].equals(keys[i - 1]))
        .mapToObj(i -> words[i])
        .toList();
  }

  String buildKey(String word) {
    return word.chars()
        .sorted()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
