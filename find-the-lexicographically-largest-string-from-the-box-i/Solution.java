import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public String answerString(String word, int numFriends) {
    return (numFriends == 1)
        ? word
        : IntStream.range(0, word.length())
            .mapToObj(
                beginIndex ->
                    word.substring(
                        beginIndex,
                        Math.min(word.length(), beginIndex + (word.length() - numFriends + 1))))
            .max(Comparator.naturalOrder())
            .get();
  }
}