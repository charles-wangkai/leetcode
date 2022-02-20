class Solution {
  static final int ALPHABET_SIZE = 26;

  public String repeatLimitedString(String s, int repeatLimit) {
    int[] counts = new int[ALPHABET_SIZE];
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
    for (int i = counts.length - 1; i >= 0; --i) {
      if (counts[i] != 0 && i != excluded - 'a') {
        return (char) (i + 'a');
      }
    }

    return 0;
  }
}