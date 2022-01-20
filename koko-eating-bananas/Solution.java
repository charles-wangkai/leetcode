import java.util.Arrays;

class Solution {
  public int minEatingSpeed(int[] piles, int h) {
    int result = -1;
    int lower = 1;
    int upper = Arrays.stream(piles).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (canEatAll(piles, h, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean canEatAll(int[] piles, int h, int eatSpeed) {
    return Arrays.stream(piles).map(pile -> (pile + eatSpeed - 1) / eatSpeed).asLongStream().sum()
        <= h;
  }
}
