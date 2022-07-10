import java.util.stream.IntStream;

class Solution {
  public boolean canChange(String start, String target) {
    int[] startIndices =
        IntStream.range(0, start.length()).filter(i -> start.charAt(i) != '_').toArray();
    int[] targetIndices =
        IntStream.range(0, target.length()).filter(i -> target.charAt(i) != '_').toArray();

    return startIndices.length == targetIndices.length
        && IntStream.range(0, targetIndices.length)
            .allMatch(
                i ->
                    start.charAt(startIndices[i]) == target.charAt(targetIndices[i])
                            && (target.charAt(targetIndices[i]) == 'L'
                                && targetIndices[i] <= startIndices[i])
                        || (target.charAt(targetIndices[i]) == 'R'
                            && targetIndices[i] >= startIndices[i]));
  }
}