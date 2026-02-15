import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String mapWordWeights(String[] words, int[] weights) {
    return Arrays.stream(words)
        .map(word -> (char) (25 - word.chars().map(c -> weights[c - 'a']).sum() % 26 + 'a'))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}