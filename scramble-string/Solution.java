import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  Map<Pair, Boolean> cache = new HashMap<>();

  public boolean isScramble(String s1, String s2) {
    if (s1.equals(s2)) {
      return true;
    }
    if (!generateKey(s1).equals(generateKey(s2))) {
      return false;
    }

    Pair pair = new Pair(s1, s2);
    if (!cache.containsKey(pair)) {
      boolean scramble =
          IntStream.range(1, s1.length())
              .anyMatch(
                  leftLength ->
                      (isScramble(s1.substring(0, leftLength), s2.substring(0, leftLength))
                              && isScramble(s1.substring(leftLength), s2.substring(leftLength)))
                          || (isScramble(
                                  s1.substring(0, leftLength),
                                  s2.substring(s1.length() - leftLength))
                              && isScramble(
                                  s1.substring(leftLength),
                                  s2.substring(0, s1.length() - leftLength))));
      cache.put(pair, scramble);
    }

    return cache.get(pair);
  }

  String generateKey(String s) {
    return s.chars().sorted().mapToObj(String::valueOf).collect(Collectors.joining(","));
  }
}

record Pair(String str1, String str2) {}
