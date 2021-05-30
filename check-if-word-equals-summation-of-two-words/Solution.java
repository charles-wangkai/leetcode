import java.util.stream.Collectors;

class Solution {
  public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
    return toNumerical(firstWord) + toNumerical(secondWord) == toNumerical(targetWord);
  }

  static int toNumerical(String s) {
    return Integer.parseInt(
        s.chars().mapToObj(ch -> String.valueOf(ch - 'a')).collect(Collectors.joining()));
  }
}
