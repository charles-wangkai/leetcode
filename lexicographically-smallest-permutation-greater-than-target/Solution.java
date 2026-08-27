import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String lexGreaterPermutation(String s, String target) {
    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    String result = "";
    for (int i = 0; i < target.length(); ++i) {
      OptionalInt greater =
          IntStream.range(target.charAt(i) - 'a' + 1, counts.length)
              .filter(j -> counts[j] != 0)
              .findFirst();
      if (greater.isPresent()) {
        --counts[greater.getAsInt()];

        result =
            "%s%c%s"
                .formatted(
                    target.substring(0, i),
                    (char) ('a' + greater.getAsInt()),
                    IntStream.range(0, counts.length)
                        .mapToObj(j -> String.valueOf((char) ('a' + j)).repeat(counts[j]))
                        .collect(Collectors.joining()));

        ++counts[greater.getAsInt()];
      }

      if (counts[target.charAt(i) - 'a'] == 0) {
        break;
      }

      --counts[target.charAt(i) - 'a'];
    }

    return result;
  }
}