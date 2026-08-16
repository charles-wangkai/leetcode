import java.util.stream.IntStream;

class Solution {
  public int maximumGap(String skill, String station) {
    int[] leftIndices = new int[skill.length()];
    int leftIndex = 0;
    for (int i = 0; i < leftIndices.length; ++i) {
      while (station.charAt(leftIndex) != skill.charAt(i)) {
        ++leftIndex;
      }

      leftIndices[i] = leftIndex;
      ++leftIndex;
    }

    int[] rightIndices = new int[skill.length()];
    int rightIndex = station.length() - 1;
    for (int i = rightIndices.length - 1; i >= 0; --i) {
      while (station.charAt(rightIndex) != skill.charAt(i)) {
        --rightIndex;
      }

      rightIndices[i] = rightIndex;
      --rightIndex;
    }

    return IntStream.range(0, skill.length() - 1)
        .map(i -> rightIndices[i + 1] - leftIndices[i])
        .max()
        .orElse(0);
  }
}