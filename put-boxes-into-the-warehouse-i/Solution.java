import java.util.Arrays;

class Solution {
  public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
    boxes = Arrays.stream(boxes).boxed().sorted().mapToInt(x -> x).toArray();

    int[] heightLimits = new int[warehouse.length];
    int heightLimit = Integer.MAX_VALUE;
    for (int i = 0; i < heightLimits.length; ++i) {
      heightLimit = Math.min(heightLimit, warehouse[i]);
      heightLimits[i] = heightLimit;
    }

    int result = 0;
    int boxIndex = 0;
    for (int i = heightLimits.length - 1; i >= 0; --i) {
      if (boxIndex != boxes.length && boxes[boxIndex] <= heightLimits[i]) {
        ++result;
        ++boxIndex;
      }
    }

    return result;
  }
}
