import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public boolean matchReplacement(String s, String sub, char[][] mappings) {
    Set<Replacement> replacements =
        Arrays.stream(mappings)
            .map(mapping -> new Replacement(mapping[0], mapping[1]))
            .collect(Collectors.toSet());

    return IntStream.rangeClosed(0, s.length() - sub.length())
        .anyMatch(
            i ->
                IntStream.range(0, sub.length())
                    .allMatch(
                        j ->
                            sub.charAt(j) == s.charAt(i + j)
                                || replacements.contains(
                                    new Replacement(sub.charAt(j), s.charAt(i + j)))));
  }
}

class Replacement {
  char from;
  char to;

  Replacement(char from, char to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  @Override
  public boolean equals(Object obj) {
    Replacement other = (Replacement) obj;

    return from == other.from && to == other.to;
  }
}