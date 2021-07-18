import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int canBeTypedWords(String text, String brokenLetters) {
    Set<Character> brokens =
        brokenLetters.chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet());

    return (int)
        Arrays.stream(text.split(" "))
            .filter(word -> word.chars().allMatch(ch -> !brokens.contains((char) ch)))
            .count();
  }
}
