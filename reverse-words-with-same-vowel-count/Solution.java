import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String reverseWords(String s) {
    String[] words = s.split(" ");
    int[] vowelNums =
        Arrays.stream(words)
            .mapToInt(word -> (int) word.chars().filter(c -> isVowel((char) c)).count())
            .toArray();

    return IntStream.range(0, words.length)
        .mapToObj(
            i ->
                (i == 0 || vowelNums[i] != vowelNums[0])
                    ? words[i]
                    : new StringBuilder(words[i]).reverse().toString())
        .collect(Collectors.joining(" "));
  }

  boolean isVowel(char letter) {
    return "aeiou".indexOf(letter) != -1;
  }
}