import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long[] maximumCoins(int[] heroes, int[] monsters, int[] coins) {
    long[] result = new long[heroes.length];
    int[] sortedHeroIndices =
        IntStream.range(0, heroes.length)
            .boxed()
            .sorted(Comparator.comparing(i -> heroes[i]))
            .mapToInt(Integer::intValue)
            .toArray();
    int[] sortedMonsterIndices =
        IntStream.range(0, monsters.length)
            .boxed()
            .sorted(Comparator.comparing(i -> monsters[i]))
            .mapToInt(Integer::intValue)
            .toArray();
    int monsterCount = 0;
    long coinSum = 0;
    for (int heroIndex : sortedHeroIndices) {
      while (monsterCount != sortedMonsterIndices.length
          && monsters[sortedMonsterIndices[monsterCount]] <= heroes[heroIndex]) {
        coinSum += coins[sortedMonsterIndices[monsterCount]];
        ++monsterCount;
      }

      result[heroIndex] = coinSum;
    }

    return result;
  }
}
