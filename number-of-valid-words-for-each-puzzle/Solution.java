import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    Map<String, Integer> patternToCount = new HashMap<>();
    for (String word : words) {
      String pattern = buildPattern(word);

      patternToCount.put(pattern, patternToCount.getOrDefault(pattern, 0) + 1);
    }

    return Arrays.stream(puzzles)
        .map(
            puzzle ->
                IntStream.range(0, 1 << (puzzle.length() - 1))
                    .map(
                        code ->
                            patternToCount.getOrDefault(
                                buildPattern(
                                    IntStream.range(0, puzzle.length())
                                        .filter(i -> i == 0 || (code & (1 << (i - 1))) != 0)
                                        .mapToObj(i -> String.valueOf(puzzle.charAt(i)))
                                        .collect(Collectors.joining())),
                                0))
                    .sum())
        .collect(Collectors.toList());
  }

  String buildPattern(String s) {
    return s.chars()
        .distinct()
        .sorted()
        .mapToObj(ch -> String.valueOf((char) ch))
        .collect(Collectors.joining());
  }
}
