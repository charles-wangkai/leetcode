import java.util.stream.Collectors;

class Solution {
  public boolean isAnagram(String s, String t) {
    return toKey(s).equals(toKey(t));
  }

  String toKey(String str) {
    return str.chars()
        .sorted()
        .mapToObj(ch -> String.valueOf((char) ch))
        .collect(Collectors.joining());
  }
}
