import java.util.stream.IntStream;

class Solution {
  public int isPrefixOfWord(String sentence, String searchWord) {
    String[] words = sentence.split(" ");

    return IntStream.range(0, words.length)
        .filter(i -> words[i].startsWith(searchWord))
        .map(i -> i + 1)
        .findFirst()
        .orElse(-1);
  }
}