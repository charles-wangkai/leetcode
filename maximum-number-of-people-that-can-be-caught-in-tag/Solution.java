import java.util.stream.IntStream;

class Solution {
  public int catchMaximumAmountofPeople(int[] team, int dist) {
    int[] zeroIndices = IntStream.range(0, team.length).filter(i -> team[i] == 0).toArray();
    int[] oneIndices = IntStream.range(0, team.length).filter(i -> team[i] == 1).toArray();

    int result = 0;
    int onePos = 0;
    for (int zeroIndex : zeroIndices) {
      while (onePos != oneIndices.length && zeroIndex > oneIndices[onePos] + dist) {
        ++onePos;
      }

      if (onePos != oneIndices.length && zeroIndex >= oneIndices[onePos] - dist) {
        ++result;
        ++onePos;
      }
    }

    return result;
  }
}