import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int stoneGameVI(int[] aliceValues, int[] bobValues) {
    int[] sortedIndices =
        IntStream.range(0, aliceValues.length)
            .boxed()
            .sorted(Comparator.<Integer>comparingInt(i -> aliceValues[i] + bobValues[i]).reversed())
            .mapToInt(x -> x)
            .toArray();

    int alicePoint = 0;
    int bobPoint = 0;
    boolean aliceTurn = true;
    for (int sortedIndex : sortedIndices) {
      if (aliceTurn) {
        alicePoint += aliceValues[sortedIndex];
      } else {
        bobPoint += bobValues[sortedIndex];
      }

      aliceTurn = !aliceTurn;
    }

    if (alicePoint > bobPoint) {
      return 1;
    } else if (alicePoint < bobPoint) {
      return -1;
    } else {
      return 0;
    }
  }
}
