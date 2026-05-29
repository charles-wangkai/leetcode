import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int passwordStrength(String password) {
    Set<Character> symbols =
        password.chars().distinct().mapToObj(c -> (char) c).collect(Collectors.toSet());

    int result = 0;
    for (char symbol : symbols) {
      if (Character.isLowerCase(symbol)) {
        result += 1;
      } else if (Character.isUpperCase(symbol)) {
        result += 2;
      } else if (Character.isDigit(symbol)) {
        result += 3;
      } else if ("!@#$".contains(String.valueOf(symbol))) {
        result += 5;
      }
    }

    return result;
  }
}