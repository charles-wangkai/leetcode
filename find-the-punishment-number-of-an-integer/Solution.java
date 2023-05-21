import java.util.stream.IntStream;

class Solution {
  public int punishmentNumber(int n) {
    return IntStream.rangeClosed(1, n)
        .filter(i -> search(String.valueOf(i * i), 0, i))
        .map(i -> i * i)
        .sum();
  }

  boolean search(String s, int index, int rest) {
    if (index == s.length()) {
      return rest == 0;
    }

    for (int nextIndex = index + 1; nextIndex <= s.length(); ++nextIndex) {
      int nextRest = rest - Integer.parseInt(s.substring(index, nextIndex));
      if (nextRest < 0) {
        break;
      }

      if (search(s, nextIndex, nextRest)) {
        return true;
      }
    }

    return false;
  }
}
