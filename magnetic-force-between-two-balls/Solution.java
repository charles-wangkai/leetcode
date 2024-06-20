import java.util.Arrays;

class Solution {
  public int maxDistance(int[] position, int m) {
    Arrays.sort(position);

    int result = -1;
    int lower = 1;
    int upper = position[position.length - 1] - position[0];
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(position, m, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int[] position, int m, int distance) {
    int count = 1;
    int lastIndex = 0;
    for (int i = 1; i < position.length; ++i) {
      if (position[i] - position[lastIndex] >= distance) {
        ++count;
        lastIndex = i;
      }
    }

    return count >= m;
  }
}