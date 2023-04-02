import java.util.Arrays;

class Solution {
  public int[] successfulPairs(int[] spells, int[] potions, long success) {
    Arrays.sort(potions);

    return Arrays.stream(spells).map(spell -> computePotionNum(potions, success, spell)).toArray();
  }

  int computePotionNum(int[] potions, long success, int spell) {
    int index = potions.length;
    int lowerIndex = 0;
    int upperIndex = potions.length - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;
      if ((long) potions[middleIndex] * spell >= success) {
        index = middleIndex;
        upperIndex = middleIndex - 1;
      } else {
        lowerIndex = middleIndex + 1;
      }
    }

    return potions.length - index;
  }
}
