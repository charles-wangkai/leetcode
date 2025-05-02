import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String pushDominoes(String dominoes) {
    int[] rightDistances = new int[dominoes.length()];
    int rightDistance = Integer.MAX_VALUE;
    for (int i = 0; i < rightDistances.length; ++i) {
      if (dominoes.charAt(i) == 'R') {
        rightDistance = 0;
      } else if (rightDistance != Integer.MAX_VALUE) {
        ++rightDistance;
      }

      rightDistances[i] = rightDistance;

      if (dominoes.charAt(i) == 'L') {
        rightDistance = Integer.MAX_VALUE;
      }
    }

    int[] leftDistances = new int[dominoes.length()];
    int leftDistance = Integer.MAX_VALUE;
    for (int i = leftDistances.length - 1; i >= 0; --i) {
      if (dominoes.charAt(i) == 'L') {
        leftDistance = 0;
      } else if (leftDistance != Integer.MAX_VALUE) {
        ++leftDistance;
      }

      leftDistances[i] = leftDistance;

      if (dominoes.charAt(i) == 'R') {
        leftDistance = Integer.MAX_VALUE;
      }
    }

    return IntStream.range(0, dominoes.length())
        .mapToObj(
            i -> {
              if (rightDistances[i] < leftDistances[i]) {
                return 'R';
              }
              if (rightDistances[i] > leftDistances[i]) {
                return 'L';
              }

              return '.';
            })
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
