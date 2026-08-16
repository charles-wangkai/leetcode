import java.util.Arrays;

class Solution {
  public int minPenalty(int period, int[] lights, int[] arrivalTime) {
    int maxLight = Arrays.stream(lights).max().getAsInt();

    return Arrays.stream(arrivalTime)
        .map(
            arrival -> {
              int r = arrival % period;

              return (r < maxLight) ? 0 : (period - r);
            })
        .max()
        .getAsInt();
  }
}