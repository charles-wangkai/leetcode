import java.util.Arrays;

class Solution {
  public int maximumValue(String[] strs) {
    return Arrays.stream(strs)
        .mapToInt(s -> s.chars().allMatch(Character::isDigit) ? Integer.parseInt(s) : s.length())
        .max()
        .getAsInt();
  }
}
