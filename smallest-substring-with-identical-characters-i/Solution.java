import java.util.stream.IntStream;

class Solution {
  public int minLength(String s, int numOps) {
    if (countDiff(s, '0') <= numOps || countDiff(s, '1') <= numOps) {
      return 1;
    }

    int result = -1;
    int lower = 2;
    int upper = s.length();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(s, numOps, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(String s, int numOps, int maxLength) {
    int operationCount = 0;
    int count = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && i != 0 && s.charAt(i) == s.charAt(i - 1)) {
        ++count;
      } else {
        operationCount += count / (maxLength + 1);

        count = 1;
      }
    }

    return operationCount <= numOps;
  }

  int countDiff(String s, char first) {
    return (int)
        IntStream.range(0, s.length())
            .filter(i -> s.charAt(i) != ((i % 2 == 0) ? first : ('0' + '1' - first)))
            .count();
  }
}