import java.util.Arrays;
import java.util.List;

class Solution {
  public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
    int maxCandy = Arrays.stream(candies).max().getAsInt();

    return Arrays.stream(candies).mapToObj(candy -> candy + extraCandies >= maxCandy).toList();
  }
}
