import java.util.stream.IntStream;

class Solution {
  public int maxDistance(String s, int k) {
    return IntStream.of(
            computeMaxDistance(s, k, 'E', 'N'),
            computeMaxDistance(s, k, 'E', 'S'),
            computeMaxDistance(s, k, 'W', 'N'),
            computeMaxDistance(s, k, 'W', 'S'))
        .max()
        .getAsInt();
  }

  int computeMaxDistance(String s, int k, char horizontalPositive, char verticalPositive) {
    int result = 0;
    int distance = 0;
    for (char direction : s.toCharArray()) {
      if (direction == horizontalPositive || direction == verticalPositive) {
        ++distance;
      } else if (k != 0) {
        --k;
        ++distance;
      } else {
        --distance;
      }

      result = Math.max(result, distance);
    }

    return result;
  }
}