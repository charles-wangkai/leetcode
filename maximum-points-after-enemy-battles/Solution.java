import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
    Arrays.sort(enemyEnergies);
    if (currentEnergy < enemyEnergies[0]) {
      return 0;
    }

    return (currentEnergy
            + IntStream.range(1, enemyEnergies.length)
                .map(i -> enemyEnergies[i])
                .asLongStream()
                .sum())
        / enemyEnergies[0];
  }
}