import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public String resultingString(String s) {
    List<Character> letters = new ArrayList<>();
    for (char letter : s.toCharArray()) {
      if (!letters.isEmpty() && isConsecutive(letter, letters.getLast())) {
        letters.removeLast();
      } else {
        letters.add(letter);
      }
    }

    return letters.stream().map(String::valueOf).collect(Collectors.joining());
  }

  boolean isConsecutive(char letter1, char letter2) {
    if (letter1 > letter2) {
      return isConsecutive(letter2, letter1);
    }

    return letter2 - letter1 == 1 || (letter1 == 'a' && letter2 == 'z');
  }
}