import java.util.Arrays;

class Solution {
  public int minimumTime(int[] hens, int[] grains) {
    Arrays.sort(hens);
    Arrays.sort(grains);

    int result = -1;
    int lower = 0;
    int upper = Integer.MAX_VALUE;
    while (lower <= upper) {
      int middle = lower + (upper - lower) / 2;
      if (check(hens, grains, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] hens, int[] grains, int time) {
    int beginGrainIndex = 0;
    for (int hen : hens) {
      if (beginGrainIndex != grains.length && Math.abs(grains[beginGrainIndex] - hen) <= time) {
        int endGrainIndex = beginGrainIndex;
        while (endGrainIndex != grains.length - 1
            && Math.min(
                        Math.abs(grains[beginGrainIndex] - hen),
                        Math.abs(grains[endGrainIndex + 1] - hen))
                    + (grains[endGrainIndex + 1] - grains[beginGrainIndex])
                <= time) {
          ++endGrainIndex;
        }

        beginGrainIndex = endGrainIndex + 1;
      }
    }

    return beginGrainIndex == grains.length;
  }
}
