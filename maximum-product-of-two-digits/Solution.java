import java.util.Comparator;

class Solution {
  public int maxProduct(int n) {
    return String.valueOf(n)
        .chars()
        .boxed()
        .sorted(Comparator.reverseOrder())
        .mapToInt(c -> c - '0')
        .limit(2)
        .reduce((acc, x) -> acc * x)
        .getAsInt();
  }
}