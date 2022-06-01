import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String discountPrices(String sentence, int discount) {
    return Arrays.stream(sentence.split(" "))
        .map(word -> update(discount, word))
        .collect(Collectors.joining(" "));
  }

  String update(int discount, String word) {
    return (word.charAt(0) == '$' && isNonNegativeRealNumber(word.substring(1)))
        ? String.format("$%.2f", Double.parseDouble(word.substring(1)) * (100 - discount) / 100)
        : word;
  }

  boolean isNonNegativeRealNumber(String s) {
    return s.chars().allMatch(c -> Character.isDigit(c) || c == '.')
        && s.chars().filter(Character::isDigit).count() >= 1
        && s.chars().filter(c -> c == '.').count() <= 1;
  }
}