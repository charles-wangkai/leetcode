import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> topStudents(
      String[] positive_feedback,
      String[] negative_feedback,
      String[] report,
      int[] student_id,
      int k) {
    Set<String> positiveWords = Arrays.stream(positive_feedback).collect(Collectors.toSet());
    Set<String> negativeWords = Arrays.stream(negative_feedback).collect(Collectors.toSet());
    int[] points =
        Arrays.stream(report)
            .mapToInt(
                s ->
                    Arrays.stream(s.split(" "))
                        .mapToInt(
                            word ->
                                positiveWords.contains(word)
                                    ? 3
                                    : (negativeWords.contains(word) ? -1 : 0))
                        .sum())
            .toArray();

    return IntStream.range(0, student_id.length)
        .boxed()
        .sorted(
            Comparator.comparing((Integer i) -> points[i])
                .reversed()
                .thenComparing(i -> student_id[i]))
        .map(i -> student_id[i])
        .limit(k)
        .toList();
  }
}
