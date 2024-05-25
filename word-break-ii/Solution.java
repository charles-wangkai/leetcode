import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> dictionary = Set.copyOf(wordDict);

    return IntStream.range(0, 1 << (s.length() - 1))
        .mapToObj(
            mask ->
                IntStream.concat(
                        IntStream.range(0, s.length() - 1).filter(i -> ((mask >> i) & 1) == 1),
                        IntStream.of(s.length() - 1))
                    .toArray())
        .map(
            indices ->
                IntStream.range(0, indices.length)
                    .mapToObj(i -> s.substring((i == 0) ? 0 : (indices[i - 1] + 1), indices[i] + 1))
                    .toArray(String[]::new))
        .filter(words -> Arrays.stream(words).allMatch(dictionary::contains))
        .map(words -> String.join(" ", words))
        .toList();
  }
}
