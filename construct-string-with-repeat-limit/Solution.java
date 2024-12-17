import java.util.stream.IntStream;

class Solution {
  public String repeatLimitedString(String s, int repeatLimit) {
    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    StringBuilder result = new StringBuilder();
    char prev = 0;
    int count = 0;
    while (true) {
      char current = findMax(counts, (count == repeatLimit) ? prev : 0);
      if (current == 0) {
        break;
      }

      --counts[current - 'a'];
      result.append(current);
      if (current == prev) {
        ++count;
      } else {
        prev = current;
        count = 1;
      }
    }

    return result.toString();
  }

  char findMax(int[] counts, char excluded) {
    return IntStream.range(0, counts.length)
        .filter(i -> counts[i] != 0 && i != excluded - 'a')
        .max()
        .stream()
        .mapToObj(i -> (char) (i + 'a'))
        .findAny()
        .orElse((char) 0);
  }
}