import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<String> twoEditWords(String[] queries, String[] dictionary) {
    return Arrays.stream(queries)
        .filter(
            query ->
                Arrays.stream(dictionary)
                    .anyMatch(
                        word ->
                            IntStream.range(0, query.length())
                                    .filter(i -> word.charAt(i) != query.charAt(i))
                                    .count()
                                <= 2))
        .toList();
  }
}
