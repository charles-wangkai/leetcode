import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int[] IMPOSSIBLE_RESULT = {-1, -1};

  public int[] threeEqualParts(int[] arr) {
    int oneNum = (int) Arrays.stream(arr).filter(bit -> bit == 1).count();
    if (oneNum == 0) {
      return new int[] {0, 2};
    }
    if (oneNum % 3 != 0) {
      return IMPOSSIBLE_RESULT;
    }

    int partOneNum = oneNum / 3;

    int rightBeginIndex = findRightBeginIndex(arr, partOneNum);
    int partLength = arr.length - rightBeginIndex;

    int leftBeginIndex = 0;
    while (arr[leftBeginIndex] == 0) {
      ++leftBeginIndex;
    }

    if (leftBeginIndex + partLength >= rightBeginIndex) {
      return IMPOSSIBLE_RESULT;
    }
    if (!isSameSequence(arr, leftBeginIndex, rightBeginIndex, partLength)) {
      return IMPOSSIBLE_RESULT;
    }

    int middleBeginIndex = leftBeginIndex + partLength;
    while (arr[middleBeginIndex] == 0) {
      ++middleBeginIndex;
    }

    if (middleBeginIndex + partLength > rightBeginIndex) {
      return IMPOSSIBLE_RESULT;
    }
    if (!isSameSequence(arr, middleBeginIndex, rightBeginIndex, partLength)) {
      return IMPOSSIBLE_RESULT;
    }

    return new int[] {leftBeginIndex + partLength - 1, middleBeginIndex + partLength};
  }

  boolean isSameSequence(int[] arr, int beginIndex1, int beginIndex2, int partLength) {
    return IntStream.range(0, partLength)
        .allMatch(i -> arr[beginIndex1 + i] == arr[beginIndex2 + i]);
  }

  int findRightBeginIndex(int[] arr, int partOneNum) {
    int oneNum = 0;
    for (int i = arr.length - 1; ; --i) {
      if (arr[i] == 1) {
        ++oneNum;

        if (oneNum == partOneNum) {
          return i;
        }
      }
    }
  }
}
