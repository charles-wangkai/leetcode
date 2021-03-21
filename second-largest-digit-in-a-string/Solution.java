import java.util.Comparator;

class Solution {
  public int secondHighest(String s) {
    return s.chars()
        .filter(Character::isDigit)
        .map(ch -> ch - '0')
        .distinct()
        .boxed()
        .sorted(Comparator.reverseOrder())
        .skip(1)
        .findFirst()
        .orElse(-1);
  }
}
