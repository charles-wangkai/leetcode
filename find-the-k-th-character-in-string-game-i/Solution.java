import java.util.stream.Collectors;

class Solution {
  public char kthCharacter(int k) {
    String word = "a";
    while (word.length() < k) {
      word +=
          word.chars()
              .mapToObj(c -> (char) ((c == 'z') ? 'a' : (c + 1)))
              .map(String::valueOf)
              .collect(Collectors.joining());
    }

    return word.charAt(k - 1);
  }
}