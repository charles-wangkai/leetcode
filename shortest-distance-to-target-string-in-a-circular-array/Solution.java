import java.util.stream.IntStream;

class Solution {
  public int closetTarget(String[] words, String target, int startIndex) {
    return IntStream.range(0, words.length)
        .filter(i -> words[i].equals(target))
        .flatMap(
            i -> IntStream.of(Math.abs(i - startIndex), words.length - Math.abs(i - startIndex)))
        .min()
        .orElse(-1);
  }
}
