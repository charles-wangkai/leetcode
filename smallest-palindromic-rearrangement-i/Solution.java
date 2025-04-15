import java.util.stream.Collectors;

class Solution {
  public String smallestPalindrome(String s) {
    String sortedHalf =
        s.substring(0, s.length() / 2)
            .chars()
            .sorted()
            .mapToObj(c -> (char) c)
            .map(String::valueOf)
            .collect(Collectors.joining());
    String middle = (s.length() % 2 == 0) ? "" : String.valueOf(s.charAt(s.length() / 2));

    return "%s%s%s"
        .formatted(sortedHalf, middle, new StringBuilder(sortedHalf).reverse().toString());
  }
}