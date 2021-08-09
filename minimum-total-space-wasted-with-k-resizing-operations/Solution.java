import java.util.Arrays;

class Solution {
  public int minSpaceWastedKResizing(int[] nums, int k) {
    int[] sizes = Arrays.stream(nums).distinct().toArray();

    int[][] minWasted = new int[k + 1][sizes.length];
    for (int i = 1; i < minWasted.length; ++i) {
      Arrays.fill(minWasted[i], Integer.MAX_VALUE);
    }
    int[][] minWastedExcluded = buildMinWastedExcluded(minWasted);

    for (int num : nums) {
      int[][] nextMinWasted = new int[k + 1][sizes.length];
      for (int i = 0; i < nextMinWasted.length; ++i) {
        Arrays.fill(nextMinWasted[i], Integer.MAX_VALUE);
      }

      for (int i = 0; i < nextMinWasted.length; ++i) {
        for (int j = 0; j < nextMinWasted[i].length; ++j) {
          if (sizes[j] >= num) {
            if (minWasted[i][j] != Integer.MAX_VALUE) {
              nextMinWasted[i][j] = Math.min(nextMinWasted[i][j], minWasted[i][j] + sizes[j] - num);
            }

            if (i != 0 && minWastedExcluded[i - 1][j] != Integer.MAX_VALUE) {
              nextMinWasted[i][j] =
                  Math.min(nextMinWasted[i][j], minWastedExcluded[i - 1][j] + sizes[j] - num);
            }
          }
        }
      }

      minWasted = nextMinWasted;
      minWastedExcluded = buildMinWastedExcluded(minWasted);
    }

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < minWasted.length; ++i) {
      for (int j = 0; j < minWasted[i].length; ++j) {
        result = Math.min(result, minWasted[i][j]);
      }
    }

    return result;
  }

  int[][] buildMinWastedExcluded(int[][] minWasted) {
    int sizeNum = minWasted[0].length;

    int[][] result = new int[minWasted.length][sizeNum];
    for (int i = 0; i < result.length; ++i) {
      int[] leftMins = new int[sizeNum];
      int leftMin = Integer.MAX_VALUE;
      for (int j = 0; j < leftMins.length; ++j) {
        leftMins[j] = leftMin;
        leftMin = Math.min(leftMin, minWasted[i][j]);
      }

      int[] rightMins = new int[sizeNum];
      int rightMin = Integer.MAX_VALUE;
      for (int j = rightMins.length - 1; j >= 0; --j) {
        rightMins[j] = rightMin;
        rightMin = Math.min(rightMin, minWasted[i][j]);
      }

      for (int j = 0; j < sizeNum; ++j) {
        result[i][j] = Math.min(leftMins[j], rightMins[j]);
      }
    }

    return result;
  }
}
