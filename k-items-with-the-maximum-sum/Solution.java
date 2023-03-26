import java.util.stream.IntStream;

class Solution {
  public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
    return IntStream.concat(
            IntStream.concat(
                IntStream.range(0, numOnes).map(i -> 1), IntStream.range(0, numZeros).map(i -> 0)),
            IntStream.range(0, numNegOnes).map(i -> -1))
        .limit(k)
        .sum();
  }
}
