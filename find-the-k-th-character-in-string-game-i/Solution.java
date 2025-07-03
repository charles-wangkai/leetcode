import java.util.stream.Collectors;

class Solution {
  public char kthCharacter(int k) {
    StringBuilder word = new StringBuilder("a");
    while (word.length() < k) {
      word.append(
          word.chars()
              .mapToObj(c -> (char) ((c == 'z') ? 'a' : (c + 1)))
              .map(String::valueOf)
              .collect(Collectors.joining()));
    }

    return word.charAt(k - 1);
  }
}