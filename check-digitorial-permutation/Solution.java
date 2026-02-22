import java.util.stream.Collectors;

class Solution {
  public boolean isDigitorialPermutation(int n) {
    int[] factorials = new int[10];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = factorials[i - 1] * i;
    }

    return generateKey(n)
        .equals(generateKey(String.valueOf(n).chars().map(c -> factorials[c - '0']).sum()));
  }

  static String generateKey(int x) {
    return String.valueOf(x)
        .chars()
        .sorted()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}