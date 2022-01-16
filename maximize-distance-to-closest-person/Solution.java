import java.util.stream.IntStream;

class Solution {
  public int maxDistToClosest(int[] seats) {
    int leftPersonIndex = -1;
    int[] leftDistances = new int[seats.length];
    for (int i = 0; i < leftDistances.length; ++i) {
      if (seats[i] == 1) {
        leftPersonIndex = i;
      }

      if (leftPersonIndex == -1) {
        leftDistances[i] = Integer.MAX_VALUE;
      } else {
        leftDistances[i] = i - leftPersonIndex;
      }
    }

    int rightPersonIndex = -1;
    int[] rightDistances = new int[seats.length];
    for (int i = rightDistances.length - 1; i >= 0; --i) {
      if (seats[i] == 1) {
        rightPersonIndex = i;
      }

      if (rightPersonIndex == -1) {
        rightDistances[i] = Integer.MAX_VALUE;
      } else {
        rightDistances[i] = rightPersonIndex - i;
      }
    }

    return IntStream.range(0, seats.length)
        .map(i -> Math.min(leftDistances[i], rightDistances[i]))
        .max()
        .getAsInt();
  }
}
