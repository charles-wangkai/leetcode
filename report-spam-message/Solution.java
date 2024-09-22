import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public boolean reportSpam(String[] message, String[] bannedWords) {
    Set<String> banned = Arrays.stream(bannedWords).collect(Collectors.toSet());

    return Arrays.stream(message).filter(banned::contains).count() >= 2;
  }
}