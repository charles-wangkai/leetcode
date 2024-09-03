import java.util.stream.Collectors;

class Solution {
  public int getLucky(String s, int k) {
    String value =
        s.chars().map(c -> c - 'a' + 1).mapToObj(String::valueOf).collect(Collectors.joining());

    for (int i = 0; i < k; ++i) {
      value = String.valueOf(value.chars().map(c -> c - '0').sum());
    }

    return Integer.parseInt(value);
  }
}
