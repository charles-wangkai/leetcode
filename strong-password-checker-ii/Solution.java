import java.util.stream.IntStream;

class Solution {
  public boolean strongPasswordCheckerII(String password) {
    return password.length() >= 8
        && password.chars().anyMatch(Character::isLowerCase)
        && password.chars().anyMatch(Character::isUpperCase)
        && password.chars().anyMatch(Character::isDigit)
        && password.chars().anyMatch(c -> "!@#$%^&*()-+".indexOf(c) != -1)
        && IntStream.range(0, password.length() - 1)
            .allMatch(i -> password.charAt(i) != password.charAt(i + 1));
  }
}