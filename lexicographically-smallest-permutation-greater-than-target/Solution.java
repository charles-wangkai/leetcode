import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String lexGreaterPermutation(String s, String target) {
    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    String result = null;
    for (int i = 0; i < target.length(); ++i) {
      int i_ = i;
      OptionalInt greater =
          IntStream.range(0, counts.length)
              .filter(j -> j > target.charAt(i_) - 'a' && counts[j] != 0)
              .findFirst();
      if (greater.isPresent()) {
        --counts[greater.getAsInt()];

        String candidate =
            "%s%c%s"
                .formatted(
                    target.substring(0, i),
                    (char) ('a' + greater.getAsInt()),
                    IntStream.range(0, counts.length)
                        .mapToObj(j -> String.valueOf((char) ('a' + j)).repeat(counts[j]))
                        .collect(Collectors.joining()));
        if (result == null || candidate.compareTo(result) < 0) {
          result = candidate;
        }

        ++counts[greater.getAsInt()];
      }

      if (counts[target.charAt(i) - 'a'] == 0) {
        break;
      }

      --counts[target.charAt(i) - 'a'];
    }

    return (result == null) ? "" : result;
  }
}