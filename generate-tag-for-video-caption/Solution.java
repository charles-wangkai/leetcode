import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String generateTag(String caption) {
    String[] words =
        Arrays.stream(caption.split(" ")).filter(x -> !x.isEmpty()).toArray(String[]::new);
    String combined =
        "#"
            + IntStream.range(0, words.length)
                .mapToObj(
                    i ->
                        (i == 0)
                            ? words[i].toLowerCase()
                            : (Character.toUpperCase(words[i].charAt(0))
                                + words[i].substring(1).toLowerCase()))
                .collect(Collectors.joining());

    return combined.substring(0, Math.min(100, combined.length()));
  }
}