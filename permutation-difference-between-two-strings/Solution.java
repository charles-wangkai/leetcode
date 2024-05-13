import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int findPermutationDifference(String s, String t) {
    Map<Character, Integer> letterToIndex =
        IntStream.range(0, s.length()).boxed().collect(Collectors.toMap(s::charAt, i -> i));

    return IntStream.range(0, t.length())
        .map(i -> Math.abs(i - letterToIndex.get(t.charAt(i))))
        .sum();
  }
}