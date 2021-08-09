class Solution {
  public int minSwaps(String s) {
    int result = 0;
    char[] brackets = s.toCharArray();
    int leftIndex = 0;
    int rightIndex = s.length() - 1;
    int depth = 0;
    for (int i = 0; i < brackets.length; ++i) {
      if (brackets[i] == '[') {
        ++depth;
      } else if (depth != 0) {
        --depth;
      } else {
        while (brackets[leftIndex] == '[') {
          ++leftIndex;
        }
        while (brackets[rightIndex] == ']') {
          --rightIndex;
        }
        brackets[leftIndex] = '[';
        brackets[rightIndex] = ']';
        ++leftIndex;
        --rightIndex;

        ++depth;
        ++result;
      }
    }

    return result;
  }
}
