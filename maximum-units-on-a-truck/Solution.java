import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int maximumUnits(int[][] boxTypes, int truckSize) {
    int[] sortedTypeIndices =
        IntStream.range(0, boxTypes.length)
            .boxed()
            .sorted(Comparator.comparing((Integer i) -> boxTypes[i][1]).reversed())
            .mapToInt(x -> x)
            .toArray();

    int result = 0;
    for (int sortedTypeIndex : sortedTypeIndices) {
      int boxNum = Math.min(boxTypes[sortedTypeIndex][0], truckSize);

      result += boxNum * boxTypes[sortedTypeIndex][1];
      truckSize -= boxNum;
    }

    return result;
  }
}
