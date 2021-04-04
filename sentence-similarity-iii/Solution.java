import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public boolean areSentencesSimilar(String sentence1, String sentence2) {
    String[] words1 = sentence1.split(" ");
    String[] words2 = sentence2.split(" ");

    if (words1.length < words2.length) {
      return areSentencesSimilar(sentence2, sentence1);
    }
    if (words1.length == words2.length) {
      return sentence1.equals(sentence2);
    }

    for (int beginIndex = 0, endIndex = words1.length - words2.length - 1;
        endIndex != words1.length;
        ++beginIndex, ++endIndex) {
      if (Stream.concat(
              IntStream.range(0, beginIndex).mapToObj(i -> words1[i]),
              IntStream.range(endIndex + 1, words1.length).mapToObj(i -> words1[i]))
          .collect(Collectors.joining(" "))
          .equals(sentence2)) {
        return true;
      }
    }

    return false;
  }
}
