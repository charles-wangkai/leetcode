import java.util.stream.Collectors;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public String decodeMessage(String key, String message) {
    char[] substituted = new char[ALPHABET_SIZE];
    char current = 'a';
    for (char c : key.toCharArray()) {
      if (c != ' ' && substituted[c - 'a'] == 0) {
        substituted[c - 'a'] = current;
        ++current;
      }
    }

    return message
        .chars()
        .mapToObj(c -> String.valueOf((c == ' ') ? ' ' : substituted[c - 'a']))
        .collect(Collectors.joining());
  }
}