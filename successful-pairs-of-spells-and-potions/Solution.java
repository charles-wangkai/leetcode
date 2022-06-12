import java.util.Arrays;

class Solution {
  public int[] successfulPairs(int[] spells, int[] potions, long success) {
    int[] sortedPotions = Arrays.stream(potions).boxed().sorted().mapToInt(x -> x).toArray();

    return Arrays.stream(spells)
        .map(spell -> computePotionNum(success, sortedPotions, spell))
        .toArray();
  }

  int computePotionNum(long success, int[] sortedPotions, int spell) {
    int index = sortedPotions.length;
    int lowerIndex = 0;
    int upperIndex = sortedPotions.length - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if ((long) sortedPotions[middleIndex] * spell >= success) {
        index = middleIndex;
        upperIndex = middleIndex - 1;
      } else {
        lowerIndex = middleIndex + 1;
      }
    }

    return sortedPotions.length - index;
  }
}