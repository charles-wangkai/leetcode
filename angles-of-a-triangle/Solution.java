// https://en.wikipedia.org/wiki/Law_of_cosines

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public double[] internalAngles(int[] sides) {
    Arrays.sort(sides);

    return (sides[0] + sides[1] > sides[2])
        ? IntStream.range(0, sides.length)
            .mapToDouble(
                i ->
                    Math.toDegrees(
                        Math.acos(
                            (sides[i] * sides[i]
                                    + sides[(i + 1) % sides.length] * sides[(i + 1) % sides.length]
                                    - sides[(i + 2) % sides.length] * sides[(i + 2) % sides.length])
                                / (2.0 * sides[i] * sides[(i + 1) % sides.length]))))
            .sorted()
            .toArray()
        : new double[0];
  }
}