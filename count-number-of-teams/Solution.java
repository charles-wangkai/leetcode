import java.util.stream.IntStream;

class Solution {
  public int numTeams(int[] rating) {
    return IntStream.range(0, rating.length)
        .map(
            j ->
                (int) IntStream.range(0, j).filter(i -> rating[i] < rating[j]).count()
                        * (int)
                            IntStream.range(j + 1, rating.length)
                                .filter(k -> rating[k] > rating[j])
                                .count()
                    + (int) IntStream.range(0, j).filter(i -> rating[i] > rating[j]).count()
                        * (int)
                            IntStream.range(j + 1, rating.length)
                                .filter(k -> rating[k] < rating[j])
                                .count())
        .sum();
  }
}