import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumBeauty(int[] flowers) {
    int[] prefixSums = new int[flowers.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + Math.max(0, flowers[i]);
    }

    Map<Integer, Integer> flowerToMinIndex = new HashMap<>();
    Map<Integer, Integer> flowerToMaxIndex = new HashMap<>();
    for (int i = 0; i < flowers.length; ++i) {
      if (!flowerToMinIndex.containsKey(flowers[i])) {
        flowerToMinIndex.put(flowers[i], i);
      }

      flowerToMaxIndex.put(flowers[i], i);
    }

    return flowerToMinIndex.keySet().stream()
        .filter(flower -> !flowerToMinIndex.get(flower).equals(flowerToMaxIndex.get(flower)))
        .mapToInt(
            flower ->
                computeSum(
                        prefixSums,
                        flowerToMinIndex.get(flower) + 1,
                        flowerToMaxIndex.get(flower) - 1)
                    + flower * 2)
        .max()
        .getAsInt();
  }

  int computeSum(int[] prefixSums, int beginIndex, int endIndex) {
    return (beginIndex <= endIndex)
        ? (prefixSums[endIndex] - ((beginIndex == 0) ? 0 : prefixSums[beginIndex - 1]))
        : 0;
  }
}
