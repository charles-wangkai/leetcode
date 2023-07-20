import java.util.List;
import java.util.stream.IntStream;

public class Solution {
  public List<Integer> filterRestaurants(
      int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
    return IntStream.range(0, restaurants.length)
        .filter(
            i ->
                (veganFriendly == 0 || restaurants[i][2] == 1)
                    && restaurants[i][3] <= maxPrice
                    && restaurants[i][4] <= maxDistance)
        .boxed()
        .sorted(
            (i1, i2) ->
                (restaurants[i1][1] != restaurants[i2][1])
                    ? -Integer.compare(restaurants[i1][1], restaurants[i2][1])
                    : -Integer.compare(restaurants[i1][0], restaurants[i2][0]))
        .map(i -> restaurants[i][0])
        .toList();
  }
}
