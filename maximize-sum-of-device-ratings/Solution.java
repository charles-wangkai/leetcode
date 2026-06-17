import java.util.Arrays;

class Solution {
  public long maxRatings(int[][] units) {
    if (units[0].length == 1) {
      return Arrays.stream(units).mapToInt(unit -> unit[0]).asLongStream().sum();
    }

    for (int[] unit : units) {
      Arrays.sort(unit);
    }

    return Arrays.stream(units).mapToInt(unit -> unit[1]).asLongStream().sum()
        - Arrays.stream(units).mapToInt(unit -> unit[1]).min().getAsInt()
        + Arrays.stream(units).mapToInt(unit -> unit[0]).min().getAsInt();
  }
}