import java.util.stream.IntStream;

class Solution {
  public int[] minOperations(String boxes) {
    return IntStream.range(0, boxes.length())
        .map(
            i ->
                IntStream.range(0, boxes.length())
                    .map(j -> (boxes.charAt(j) - '0') * Math.abs(j - i))
                    .sum())
        .toArray();
  }
}
