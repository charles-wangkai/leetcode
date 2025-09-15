import java.util.Arrays;

class Solution {
  public int canBeTypedWords(String text, String brokenLetters) {
    return (int)
        Arrays.stream(text.split(" "))
            .filter(
                word ->
                    word.chars().allMatch(c -> !brokenLetters.contains(String.valueOf((char) c))))
            .count();
  }
}
