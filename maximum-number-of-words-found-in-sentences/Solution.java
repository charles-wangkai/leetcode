import java.util.Arrays;

class Solution {
  public int mostWordsFound(String[] sentences) {
    return Arrays.stream(sentences)
        .mapToInt(sentence -> sentence.split(" ").length)
        .max()
        .getAsInt();
  }
}