import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<List<String>> partition(String s) {
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
                    .toList())
        .filter(parts -> parts.stream().allMatch(this::isPalindrome))
        .toList();
  }

  boolean isPalindrome(String s) {
    return new StringBuilder(s).reverse().toString().equals(s);
  }
}
