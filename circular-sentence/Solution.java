import java.util.stream.IntStream;

class Solution {
  public boolean isCircularSentence(String sentence) {
    String[] words = sentence.split(" ");

    return IntStream.range(0, words.length)
        .allMatch(
            i -> words[i].charAt(words[i].length() - 1) == words[(i + 1) % words.length].charAt(0));
  }
}
